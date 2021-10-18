cd src
java -cp "launcher/cup.jar:." launcher.Main ../tests/codigo/fibonacci/fibonacci.txt
cd ../tests/codigo/fibonacci
../wat2wasm fibonacci.wat
echo "  Salida"
node main.js
