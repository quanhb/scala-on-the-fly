A small library to allow compiling classes from classpath/string.

Execute script:
```scala
val compiler = new OnTheFlyCompiler(None)
val script   = "println(\"you should see me\")"

compiler.eval[Unit](script)
```

Compile class and execute a method on it:
```scala
val compiler = new OnTheFlyCompiler(None)
val script = "import de.codepitbull.scala.onthefly.Extendthis\n" +
             "class Test extends Extendthis{\n" +
             "override def hello():String = \"hello\"\n" +
             "}"
             
compiler.compileClass(script)

compiler
  .findClass("Test")
  .get
  .newInstance()
  .asInstanceOf[Extendthis]
  .hello()
```

Alternative: https://github.com/kostaskougios/scalascriptengine
