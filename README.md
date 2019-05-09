# Assignment 1
## How to Compile
```
./build.sh
```
This will create a App.jar file in *./dist/*
## How to Use
After you compiled the programm into *./dist/* run: 
```
java -jar ./dist/App.jar
```
or simply execute:
```
./start.sh
```
You can pass parameters:
```
./start.sh --help
Usage: <main class> [-h] [-i=<file>] [-t=<outputFormat>]
  -h, --help           display this help message
  -i, --input=<file>   a file to convert
  -t, --type=<outputFormat> the output format. Valid values: tgf, graphviz (default: graphviz)
```
This programm will convert graphs that are passed in the TGF format into either graphviz or the TGF format. :D
```bash
# Convert tgf to graphviz and output in terminal
./start.sh -i ./start.sh -i german-cities.tgf

# Convert and specify output type (default: graphviz)
./start.sh -i ./start.sh -i german-cities.tgf -t tgf

# Convert tgf and pipe output to graphviz dot command
./start.sh -i ./start.sh -i german-cities.tgf | dot -Tpng > output.png
```