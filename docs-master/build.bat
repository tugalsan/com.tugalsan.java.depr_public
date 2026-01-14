mvn clean -DskipTests install
mvn -Pprod -DskipTests clean install
echo see docs-web/target
pause