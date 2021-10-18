cd src
ruta="../tests/errores/errores_vinculacion"

echo '\n\033[1mTEST DE COMPROBACION DE ERRORES\033[0m'
echo '\033[3mErrores detectados por vinculacion\033[0m\n'

echo '+ Devuelve error por declaracion de variables con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/variables_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por declaracion de structs con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/structs_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por declaracion de enums con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/enums_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por declaracion de vectores con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/vectores_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por declaracion de punteros con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/punteros_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por declaracion de funciones con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/funciones_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por haber parametros con el mismo nombre'
java -cp "launcher/cup.jar:." launcher.Main $ruta/parametros_mismo_nombre.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por asignacion a variable no declarada'
java -cp "launcher/cup.jar:." launcher.Main $ruta/variable_no_declarada.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por asignacion a identificador no asignable'
java -cp "launcher/cup.jar:." launcher.Main $ruta/asignacion_no_asignable.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por acceso a llamada de una funcion que no existe'
java -cp "launcher/cup.jar:." launcher.Main $ruta/acceso_funcion_no_declarada.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por llamada a funcion de tipo void no declarada'
java -cp "launcher/cup.jar:." launcher.Main $ruta/llamada_funcion_void_no_declarada.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por acceso a vector que no esta declarado'
java -cp "launcher/cup.jar:." launcher.Main $ruta/acceso_vector_no_declarado.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por acceso a un tipo personal que no esta declarado'
java -cp "launcher/cup.jar:." launcher.Main $ruta/acceso_tipo_personal_no_declarado.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por acceso al atributo de un tipo personal que no tiene la declaracion correspondiente'
java -cp "launcher/cup.jar:." launcher.Main $ruta/acceso_atributo_no_declarado.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por acceso a un vector de tipo personal que no esta declarado'
java -cp "launcher/cup.jar:." launcher.Main $ruta/acceso_vector_tipo_personal_no_declarado.txt
read -p "> Pulsa enter para continuar"

echo '+ Devuelve error por acceso a un tipo personal del vector que no esta declarado'
java -cp "launcher/cup.jar:." launcher.Main $ruta/acceso_tipo_personal_vector_no_declarado.txt
read -p "> Pulsa enter para continuar"
