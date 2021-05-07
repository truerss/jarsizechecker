
# jarsizechecker

Tool for checking actual size of files/dirs inside jar archive.

# usage

```
usage: java -jar jarsizechecker.jar -j /path/to/jar
 -d,--deep <arg>    Deep tree level, default is 2
 -h,--help          print this message
 -i,--include       Display files, default is false
 -j,--jar <arg>     Path to the jar file
 -o,--order <arg>   Sort oder, default is DESC
 -u,--unfold        Display trees with single leaf, default is false

```

### default 

```
java -jar jarsizechecker.jar -j /tmp/path/sources/truerss_1.0.5.jar

/com                                               18.0 MB
  /com/mysql                                          6.0 MB
  /com/fasterxml                                      4.8 MB
  /com/google                                         4.4 MB
  /com/github                                         1.8 MB
  /com/typesafe                                       640.3 kB
  /com/zaxxer                                         355.0 kB
/scala                                             16.7 MB
  /scala/collection                                   7.9 MB
  /scala/jdk                                          1.9 MB
  /scala/compat                                       1.4 MB
  /scala/xml                                          1.2 MB
  /scala/runtime                                      678.6 kB
  /scala/math                                         648.7 kB
  /scala/util                                         454.2 kB
  /scala/concurrent                                   425.3 kB
...

Uncompressed size: 94.6 MB
Compressed size: 41.4 MB

```

### deep parameter

```
java -jar jarsizechecker.jar -j /tmp/path/sources/truerss_1.0.5.jar -d 3

/com                                                                                                     18.0 MB
  /com/mysql                                                                                                6.0 MB
    /com/mysql/cj                                                                                              6.0 MB
    /com/mysql/jdbc                                                                                            3.2 kB

```

### unfold single files (when dir contain only one subdir)

```
java -jar jarsizechecker.jar -j /tmp/path/sources/truerss_1.0.5.jar -u

/play                                              2.8 MB
  /play/api                                           2.8 MB
/izumi                                             2.1 MB
  /izumi/reflect                                      2.1 MB
/ch                                                1.3 MB
  /ch/qos                                             1.3 MB

```

# LICENSE: MIT


