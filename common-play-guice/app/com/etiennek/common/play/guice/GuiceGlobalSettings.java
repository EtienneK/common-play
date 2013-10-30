package com.etiennek.common.play.guice;

import play.Application;
import play.GlobalSettings;
import play.Logger;


public class GuiceGlobalSettings extends GlobalSettings {
	@Override
	public void onStart(Application application) {
		Logger.info("On start!!!!!");
	}
}
