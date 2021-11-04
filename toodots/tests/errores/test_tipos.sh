cd src
ruta="../tests/errores/errores_tipo"

echo '\n\033[1mTEST DE COMPROBACION DE ERRORES\033[0m'
echo '\033[3mErrores detectados por comprobacion de tipos\033[0m\n'

echo '+ Devuelve error porque se declara una variable con asignacion incorrecta'
java -cp "launcher/cup.jar:." launcher.Main $ruta/dvariable_asignacion_incorrecta.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque se declara un vector cuyo tipo no corresponde con el de su asignacion'
java -cp "launcher/cup.jar:." launcher.Main $ruta/dvector_tipo_incorrecto.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque se declara un puntero con asignacion incorrecta'
java -cp "launcher/cup.jar:." launcher.Main $ruta/dpuntero_asignacion_incorrecta.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque se declara una funcion que no devuelve el tipo que deberia'
java -cp "launcher/cup.jar:." launcher.Main $ruta/dfuncion_tipo_return_incorrecto.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque la condicion del bucle/condicional no es de tipo Bool'
java -cp "launcher/cup.jar:." launcher.Main $ruta/condicion_no_bool.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque los tipos de la asignacion no son correctos'
java -cp "launcher/cup.jar:." launcher.Main $ruta/asignacion_tipos_incorrectos.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque se hace una llamada que devuelve un valor y no se recoge'
java -cp "launcher/cup.jar:." launcher.Main $ruta/llamada_no_void_sola.txt
read -p "> Pulsa enter para continuar"



echo '\n+ Devuelve error porque los tipos de la expresion binaria no son correctos'
java -cp "launcher/cup.jar:." launcher.Main $ruta/ebin_tipos_incorrectos.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque el tipo de la expresion unaria no es correcto'
java -cp "launcher/cup.jar:." launcher.Main $ruta/eun_tipos_incorrectos.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque el array tiene elementos de distinto tipo'
java -cp "launcher/cup.jar:." launcher.Main $ruta/array_tipos_incorrectos.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque el acceso a llamada tiene argumentos de tipo incorrecto'
java -cp "launcher/cup.jar:." launcher.Main $ruta/allamada_tipos_argumentos_incorrectos.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque el acceso a llamada tiene un numero de argumentos incorrecto'
java -cp "launcher/cup.jar:." launcher.Main $ruta/allamada_num_argumentos_incorrectos.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque la posicion del acceso al array no es de tipo Int'
java -cp "launcher/cup.jar:." launcher.Main $ruta/avector_posicion_no_entera.txt
read -p "> Pulsa enter para continuar"

echo '\n+ Devuelve error porque el identificador del tipo personal no existe'
java -cp "launcher/cup.jar:." launcher.Main $ruta/enum_identificador_inexistente.txt
