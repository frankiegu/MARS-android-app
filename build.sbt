scalaVersion := "2.11.8"
scalacOptions += "-Xexperimental"

enablePlugins(AndroidApp)
packagingOptions := PackagingOptions(
  excludes =
    "META-INF/notice.txt" ::
      "META-INF/license.txt" ::
      "META-INF/LICENSE" ::
      "META-INF/NOTICE" ::
      "META-INF/LICENSE.txt" ::
      "META-INF/NOTICE.txt" ::
      Nil
)

proguardOptions ++= ProguardSettings.options
proguardCache ++= ProguardSettings.cache

resolvers ++= Seq(
  "jitpack" at "https://jitpack.io",
  "jcenter" at "https://jcenter.bintray.com/"
)

// android support libs
platformTarget := "android-23"
val androidSupportV = "23.2.0"
javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil
useProguard := true
dexMulti := true
libraryDependencies ++= Seq(
  "com.android.support" % "design"          % androidSupportV,
  "com.android.support" % "appcompat-v7"    % androidSupportV,
  "com.android.support" % "cardview-v7"     % androidSupportV,
  "com.android.support" % "recyclerview-v7" % androidSupportV
)

// logging
libraryDependencies ++= Seq(
  "com.noveogroup.android" % "android-logger"         % "1.3.5",
  "com.typesafe.scala-logging" %% "scala-logging"     % "3.1.0"
)

libraryDependencies ++= Seq(
  "org.scaloid"                     %% "scaloid"                     % "4.0",   // https://github.com/pocorall/scaloid
  "com.pnikosis"                    %  "materialish-progress"        % "1.7",   // https://github.com/pnikosis/materialish-progress
  "com.github.clans"                %  "fab"                         % "1.6.1", // https://github.com/Clans/FloatingActionButton
  "com.makeramen"                   %  "roundedimageview"            % "2.2.1", // https://github.com/vinc3m1/RoundedImageView
  "com.rengwuxian.materialedittext" %  "library"                     % "2.1.4", // https://github.com/rengwuxian/MaterialEditText,
  "com.github.nscala-time"          %% "nscala-time"                 % "2.6.0", // https://github.com/nscala-time/nscala-time
  "org.scalaj"                      %% "scalaj-http"                 % "2.2.0", // https://github.com/scalaj/scalaj-http
  "com.pixplicity.easyprefs"        %  "library"                     % "1.7",   // https://github.com/Pixplicity/EasyPreferences
  "com.typesafe.play"               %% "play-json"                   % "2.4.0-M2", // https://www.playframework.com/documentation/2.3.x/ScalaJsonCombinators
  "com.github.cb372"                %% "scalacache-guava"            % "0.7.5",
  "com.github.dmytrodanylyk"        %  "android-morphing-button"     % "98a4986e56", // https://github.com/dmytrodanylyk/android-morphing-button
  "jp.satorufujiwara"               % "material-scrolling"           % "1.1.0",
  "com.github.ksoichiro"            % "android-observablescrollview" % "1.6.0",
  "com.skocken"                     % "efficientadapter.lib"         % "2.0",
  "com.makeramen"                   % "roundedimageview"             % "2.2.1",
  "com.github.florent37"            % "viewanimator"                 % "1.0.0",
  "com.github.adnan-SM"             % "TimelyTextView"               % "ead3c48eaa",
  "org.scalactic"                  %% "scalactic"                    % "2.2.6",
  "com.dlazaro66.qrcodereaderview"  % "qrcodereaderview"             % "1.0.0",
  "com.github.bumptech.glide"       % "glide"                        % "3.6.1",
  "com.github.tajchert"             % "nammu"                        % "1.1.1"
)

run <<= run in Android
install <<= install in Android