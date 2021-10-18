cd src
java -cp "launcher/cup.jar:." launcher.Main ../tests/codigo/bubbleSort/bubbleSort.txt
cd ../tests/codigo/bubbleSort
../wat2wasm bubbleSort.wat
echo "  Salida"
node main.js
