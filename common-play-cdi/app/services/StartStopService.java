package services;

import javax.enterprise.context.ApplicationScoped;

import play.Logger;

import com.google.common.util.concurrent.AbstractService;

@ApplicationScoped
public class StartStopService extends AbstractService {

	@Override
	protected void doStart() {
		Logger.info("Started the service");
		notifyStarted();
	}

	@Override
	protected void doStop() {
		Logger.info("Stopped the service");
		notifyStopped();
	}

}
