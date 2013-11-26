How to use (OS X with Homebrew)
==

Install GlassFish
--
    $ brew install glassfish
    $ mv /usr/local/Cellar/glassfish/4.0/libexec/glassfish/modules/jersey-gf-cdi.jar /usr/local/Cellar/glassfish/4.0/libexec/glassfish/modules/../
    $ wget -P /usr/local/Cellar/glassfish/4.0/libexec/glassfish/modules http://repo1.maven.org/maven2/org/glassfish/jersey/containers/glassfish/jersey-gf-cdi/2.0/jersey-gf-cdi-2.0.jar

by [http://qiita.com/opengl-8080/items/cfdde684b36efec50477](http://qiita.com/opengl-8080/items/cfdde684b36efec50477)

Build camellia
--
    $ brew install maven
    $ git clone git@github.com:nagaseyasuhito/camellia.git
    $ cd camellia
    $ mvn verify
