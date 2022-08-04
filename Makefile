
JAR_NAME = test

linux: macos

macos:
	@./gradlew jar
	@mv backend.jar $(JAR_NAME).jar
	@jar -uf $(JAR_NAME).jar manifest.json
	@jar -tf $(JAR_NAME).jar
	@echo cleaning jar...
	@zip -d $(JAR_NAME).jar com/compensation/portal/plugins/CompensationPlugin.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/plugins/PluginFieldTypes.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/dataclasses/ValidityCheckable.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/dataclasses/compensation/HistoricalData.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/dataclasses/compensation/Compensable*.class
	@echo finished cleaning jar:
	@jar -tf $(JAR_NAME).jar

windows:
	@./gradlew jar
	@ren backend.jar $(JAR_NAME).jar
	@jar -uf $(JAR_NAME).jar manifest.json
	@jar -tf $(JAR_NAME).jar
	@echo cleaning jar...
	@zip -d $(JAR_NAME).jar com/compensation/portal/plugins/CompensationPlugin.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/plugins/PluginFieldTypes.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/dataclasses/ValidityCheckable.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/dataclasses/compensation/HistoricalData.class
	@zip -d $(JAR_NAME).jar com/compensation/portal/dataclasses/compensation/Compensable*.class
	@echo finished cleaning jar:
	@jar -tf $(JAR_NAME).jar

