import java.io.File

import com.typesafe.config.ConfigFactory

import models.domains.DomainsDDL
import play.api.Application
import play.api.Configuration
import play.api.GlobalSettings
import play.api.Logger
import play.api.Mode

object Global extends GlobalSettings {

  override def onLoadConfig(config: Configuration, path: File, classloader: ClassLoader, mode: Mode.Mode): Configuration = {
    Logger.info("Apllication  configuration file is loading with " + mode.toString + "  mode")
    val modeSpecificConfig = config ++ Configuration(ConfigFactory.load(s"${mode.toString.toLowerCase}.conf"))
    super.onLoadConfig(modeSpecificConfig, path, classloader, mode)
  }

  override def onStart(app: Application): Unit = {
    Logger.info("Apllication has been started")
    DomainsDDL.create
  }

  override def onStop(app: Application): Unit = {
    Logger.info("Application shutdown.......")
  }

}

	