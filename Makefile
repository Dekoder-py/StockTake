build-executable:
	jpackage \
  --type app-image \
  --name StockTake \
  --input target \
  --main-jar StockTake-1.0-SNAPSHOT.jar \
  --main-class dev.codingcorner.Main
