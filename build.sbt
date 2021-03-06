name := "playwithslick2withunittest"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
                            jdbc,
                            "com.typesafe.slick"  %%   "slick"                %     "2.0.0",
                            "postgresql"          %    "postgresql"           %     "9.1-901.jdbc4",
                            "mysql"               %    "mysql-connector-java" %     "5.1.23",
                            "org.mockito"         %    "mockito-core"         %    "1.9.5"           %  "test",
                            "com.h2database"      %    "h2"                   %     "1.3.166"        %  "test"
)    


play.Project.playScalaSettings
