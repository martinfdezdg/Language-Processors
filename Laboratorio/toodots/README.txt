PROCEDIMIENTO DE COMPILACIÓN:
(Abrir terminal en el directorio /toodots)

- Compilación AnalizadorLexicoTiny.l:
$ sh compile/compila_alex.sh

- Compilación Tiny.cup:
$ sh compile/compila_asint.sh

- Compilación proyecto completo:
$ sh compile/compila.sh


PRUEBAS AUTOMÁTICAS
(Abrir terminal en el directorio /toodots)

- Pruebas automáticas de muestreo
$ sh tests/basicos/"nombre".sh
$ sh tests/clases/"nombre".sh

- Pruebas automáticas de comprobación y vinculación
$ sh tests/errores/test_tipos.sh
$ sh tests/errores/test_vinculacion.sh

- Pruebas automáticas de generación de código
$ sh tests/codigo/"nombre".sh
