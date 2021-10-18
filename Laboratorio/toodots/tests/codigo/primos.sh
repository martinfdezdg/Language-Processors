cd src
java -cp "launcher/cup.jar:." launcher.Main ../tests/codigo/primos/primos.txt
cd ../tests/codigo/primos
../wat2wasm primos.wat
echo "  Salida"
node main.js
