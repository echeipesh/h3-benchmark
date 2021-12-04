# Specialization boxing example and benchmark

This projects shows the difference how the not careful functions override of the specialized parent trait can lead to the boxing overhead.

## Run benchmarks to generate JFR output

```bash
$ sbt 
$ clean
$ benchOnly com/pomadchin/raster/bench/LocalTileBench.scala
$ benchOnly com/pomadchin/raster/bench/LocalTileBoxingBench.scala
```

## Results 

All inheritents of the original specialized trait, should have narrowed specialized functions declarations.
The absence of it in one of the classes in the hierarchy leads to the non specialized method 
invocation.

The output could be opened via [JDK Mission Control](https://adoptopenjdk.net/jmc.html) with the following results:

### LocalTileBoxingBench

![boxing](https://user-images.githubusercontent.com/4929546/144718454-91f3a258-dc1f-4243-9114-a986b33c42da.png)

### LocalTileBench

![unboxed](https://user-images.githubusercontent.com/4929546/144718504-93ce6299-b52d-45ea-aba7-7be373f795dd.png)
