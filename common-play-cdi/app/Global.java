import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;

import play.Application;
import play.GlobalSettings;
import play.Logger;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;

public class Global extends GlobalSettings {

	private CdiContainer cdiContainer;
	private ServiceManager serviceManager;

	@Override
	public void onStart(Application application) {
		cdiContainer = CdiContainerLoader.getCdiContainer();
		cdiContainer.boot();

		ContextControl contextControl = cdiContainer.getContextControl();
		contextControl.startContext(ApplicationScoped.class);

		List<Service> services = BeanProvider.getContextualReferences(
				Service.class, true);

		serviceManager = new ServiceManager(services).startAsync();
		try {
			serviceManager.awaitHealthy(1L, TimeUnit.MINUTES);
		} catch (TimeoutException timeoutException) {
			Logger.error("Timed out while waiting for services to start",
					timeoutException);
			Throwables.propagate(timeoutException);
		}
	}

	@Override
	public void onStop(Application application) {
		serviceManager.stopAsync();
		try {
			serviceManager.awaitStopped(1L, TimeUnit.MINUTES);
		} catch (TimeoutException timeoutException) {
			Logger.error("Timed out while waiting for services to stop",
					timeoutException);
		} finally {
			cdiContainer.shutdown();
		}
	}

	@Override
	public <A> A getControllerInstance(Class<A> clazz) {
		return BeanProvider.getContextualReference(
				cdiContainer.getBeanManager(), clazz, false);
	}

}