# Instrucciones de compilación y ejecución
## Procedimiento de compilación
> Abrir terminal en el directorio /toodots

Compilación AnalizadorLexicoTiny.l:
```shell
sh compile/compila_alex.sh
```

Compilación Tiny.cup:
```shell
sh compile/compila_asint.sh
```

Compilación proyecto completo:
```shell
sh compile/compila.sh
```

## Pruebas automáticas
> Abrir terminal en el directorio /toodots

Pruebas automáticas de muestreo
```shell
sh tests/basicos/"nombre".sh
sh tests/clases/"nombre".sh
```

Pruebas automáticas de comprobación y vinculación
```shell
sh tests/errores/test_tipos.sh
sh tests/errores/test_vinculacion.sh
```

Pruebas automáticas de generación de código
```shell
sh tests/codigo/"nombre".sh
```
