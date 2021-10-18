cd src
java -cp "launcher/cup.jar:." launcher.Main ../tests/codigo/factorial/factorial.txt
cd ../tests/codigo/factorial
../wat2wasm factorial.wat
echo "  Salida"
node main.js
