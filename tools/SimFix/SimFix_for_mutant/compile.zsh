cd src
javac -XDignore.symbol.file=true -classpath "../lib/*" $(find . -name "*.java") -d ../bin