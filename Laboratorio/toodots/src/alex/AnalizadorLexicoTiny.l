package alex;
import errors.GestionErroresTiny;

%% 
%cup
%line
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
    this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = [A-Z]|[a-z]
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}*|0)
parteDecimal = {digito}* {digitoPositivo}

Objects = OBJECTS
Process = PROCESS

separador = [ \t\r\b\n]
comentario = \(\([^\)\)]*\)\)
mas = \+
menos = -
asignacion = :=
bucle = @

int = Int
float = Float
bool = Bool
char = Char
void = Void
struct = Struct
enum = Enum

identificador = ({letra})({letra}|{digito})*
numeroEntero = [\+,\-]?{parteEntera}
numeroReal = [\+,\-]?{parteEntera}\.{parteDecimal}
cierto = true
falso = false
caracter = \"[^\n]\"

opNegacion = not
opMultiplicacion = \*
opDivision = /
opModulo = \%
opMenor = <
opMayor = >
opMenorIgual = <=
opMayorIgual = >=
opIgual = ==
opDistinto = ><
opConjuncion = and
opDisyuncion = or
opImprimir = >>

parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
llaveCierre = \}
interrogacionCierre = \?
exclamacionCierre = !
coma  = ,
punto  = \.
dosPuntos = :

%%
{Objects}                  {return ops.unidadObjects();}
{Process}                  {return ops.unidadProcess();}
{separador}                {}
{comentario}               {}
{mas}                      {return ops.unidadMas();}
{menos}                    {return ops.unidadMenos();}
{bucle}                    {return ops.unidadBucle();}
{asignacion}               {return ops.unidadAsignacion();}
{int}                      {return ops.unidadInt();}
{float}                    {return ops.unidadFloat();}
{bool}                     {return ops.unidadBool();}
{char}                     {return ops.unidadChar();}
{void}                     {return ops.unidadVoid();}
{struct}                   {return ops.unidadStruct();}
{enum}                     {return ops.unidadEnum();}
{numeroEntero}             {return ops.unidadEntero();}
{numeroReal}               {return ops.unidadReal();}
{cierto}                   {return ops.unidadTrue();}
{falso}                    {return ops.unidadFalse();}
{caracter}                 {return ops.unidadCaracter();}
{opNegacion}               {return ops.unidadNegacion();}
{opMultiplicacion}         {return ops.unidadMultiplicacion();}
{opDivision}               {return ops.unidadDivision();}
{opModulo}                 {return ops.unidadModulo();}
{opMenor}                  {return ops.unidadMenor();}
{opMayor}                  {return ops.unidadMayor();}
{opMenorIgual}             {return ops.unidadMenorIgual();}
{opMayorIgual}             {return ops.unidadMayorIgual();}
{opIgual}                  {return ops.unidadIgual();}
{opDistinto}               {return ops.unidadDistinto();}
{opConjuncion}             {return ops.unidadConjuncion();}
{opDisyuncion}             {return ops.unidadDisyuncion();}
{opImprimir}               {return ops.unidadImprimir();}
{identificador}            {return ops.unidadIdentificador();}
{parentesisApertura}       {return ops.unidadParentesisApertura();}
{parentesisCierre}         {return ops.unidadParentesisCierre();}
{corcheteApertura}         {return ops.unidadCorcheteApertura();}
{corcheteCierre}           {return ops.unidadCorcheteCierre();}
{llaveCierre}              {return ops.unidadLlaveCierre();}
{interrogacionCierre} {return ops.unidadInterrogacionCierre();}
{exclamacionCierre}        {return ops.unidadExclamacionCierre();}
{coma}                     {return ops.unidadComa();}
{punto}                    {return ops.unidadPunto();}
{dosPuntos}                {return ops.unidadDosPuntos();}
[^]                        {ops.error();}