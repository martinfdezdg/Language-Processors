package alex;
import errors.GestionErroresTiny;


public class AnalizadorLexicoTiny implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
    this.errores = errores;
  }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoTiny (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoTiny (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoTiny () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"12:8,42:2,10,12:2,42,12:18,42,54,41,12:2,45,12:2,11,13,43,14,34,15,37,44,36" +
",35:9,17,12,46,18,47,53,16,48,2,5,48,4,22,48:2,19,3,48:4,1,8,48,9,7,6,48,28" +
",48:4,50,12,51,12:3,25,49,32,30,38,39,49,26,29,49:2,23,33,20,24,49:2,27,40," +
"21,31,49:5,12:2,52,12:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,101,
"0,1,2,1,3,1:2,4:2,1,5,4,6,1:4,7,8,1:7,9,10,1:4,9:3,1,9:3,1,9:7,11,12,13,14," +
"15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39," +
"40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,9,61,9,62")[0];

	private int yy_nxt[][] = unpackFromString(63,55,
"1,2,80,99,81,82,99,95,100,99,3,4,5,6,7,8,9,10,49,67,68,83,90,99,48,69,99:2," +
"84,99:5,11,12,50,13,99,91,99,53,3,14,15,16,17,18,99:2,19,20,21,22,23,-1:56," +
"99,96,99:7,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:16,47" +
",-1:78,12,50,-1:36,24,-1:71,12:2,27,-1:35,28,-1:54,29,-1:27,30,31,-1:8,99:9" +
",-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:40,27:2,-1:19,4" +
"7:12,54,47:41,-1,99:9,-1:9,99,97:2,99,97:4,26,99,97:5,-1,97:2,-1,97:3,-1:7," +
"99,97,-1:23,25,-1:73,27,-1:58,35,-1:14,99:9,-1:9,99,97,32,99,97:5,99,97:5,-" +
"1,97:2,-1,97:3,-1:7,99,97,-1:6,51:9,-1,51:44,-1:13,39,-1:42,99:9,-1:9,99,97" +
",33,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97" +
":5,99,97,34,97:3,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,36,97" +
":4,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,99,97:" +
"4,37,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:4,38,99,97:5,-" +
"1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1" +
",40,97:2,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,99,97,41,97:3,-1,97:2,-1" +
",97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97,42,99,97:5,99,97:5,-1,97:2,-1,97:3,-1" +
":7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,43,97:2,-1:7,99," +
"97,-1:6,99:9,-1:9,99,97,44,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6," +
"99:6,45,99:2,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,9" +
"9:6,46,99:2,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99" +
":9,-1:9,99,52,97,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9," +
"99,97:2,99,97,55,97:3,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99," +
"56,97,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99," +
"97,57,97:3,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:" +
"5,99,97:2,58,97:2,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:2" +
",59,97:2,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5," +
"99,97:2,60,97:2,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,9" +
"9,61,97:4,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:2,62,97:2" +
",99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,99,97:5," +
"-1,97:2,-1,97:2,63,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:5,99,97:3,64,97," +
"-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:5,65,99:3,-1:9,99,97:2,99,97:5,99,97:5,-" +
"1,97:2,-1,97:3,-1:7,99,97,-1:6,99:6,66,99:2,-1:9,99,97:2,99,97:5,99,97:5,-1" +
",97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97,70,97:3,99,97:5,-1,97" +
":2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,71,97,99,97:5,99,97:5,-1,97:2,-1,97" +
":3,-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:3,72,97,99,97:5,-1,97:2,-1,97:3," +
"-1:7,99,97,-1:6,99:9,-1:9,99,97:2,99,97:4,73,99,97:5,-1,97:2,-1,97:3,-1:7,9" +
"9,97,-1:6,99:9,-1:9,99,97:2,99,97,74,97:3,99,97:5,-1,97:2,-1,97:3,-1:7,99,9" +
"7,-1:6,99:9,-1:9,99,97:2,99,97,75,97:3,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-" +
"1:6,99:9,-1:9,99,97:2,99,76,97:4,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99" +
":9,-1:9,99,97:2,99,97:5,99,97:2,77,97:2,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:" +
"4,78,99:4,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:3" +
",79,99:5,-1:9,99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9," +
"-1:9,99,97:2,99,85,97:4,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,9" +
"9,97:2,99,97:2,86,97:2,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99" +
",97:2,99,97:4,87,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:3,88,99:5,-1:9," +
"99,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:4,89,99:4,-1:9,9" +
"9,97:2,99,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:9,-1:9,99,97,92,9" +
"9,97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:2,93,99:6,-1:9,99,97:2,99" +
",97:5,99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,94,99:8,-1:9,99,97:2,99,97:5," +
"99,97:5,-1,97:2,-1,97:3,-1:7,99,97,-1:6,99:8,98,-1:9,99,97:2,99,97:5,99,97:" +
"5,-1,97:2,-1,97:3,-1:7,99,97,-1:5");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return ops.unidadIdentificador();}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return ops.unidadParentesisApertura();}
					case -5:
						break;
					case 5:
						{ops.error();}
					case -6:
						break;
					case 6:
						{return ops.unidadParentesisCierre();}
					case -7:
						break;
					case 7:
						{return ops.unidadMas();}
					case -8:
						break;
					case 8:
						{return ops.unidadMenos();}
					case -9:
						break;
					case 9:
						{return ops.unidadBucle();}
					case -10:
						break;
					case 10:
						{return ops.unidadDosPuntos();}
					case -11:
						break;
					case 11:
						{return ops.unidadComa();}
					case -12:
						break;
					case 12:
						{return ops.unidadEntero();}
					case -13:
						break;
					case 13:
						{return ops.unidadPunto();}
					case -14:
						break;
					case 14:
						{return ops.unidadMultiplicacion();}
					case -15:
						break;
					case 15:
						{return ops.unidadDivision();}
					case -16:
						break;
					case 16:
						{return ops.unidadModulo();}
					case -17:
						break;
					case 17:
						{return ops.unidadMenor();}
					case -18:
						break;
					case 18:
						{return ops.unidadMayor();}
					case -19:
						break;
					case 19:
						{return ops.unidadCorcheteApertura();}
					case -20:
						break;
					case 20:
						{return ops.unidadCorcheteCierre();}
					case -21:
						break;
					case 21:
						{return ops.unidadLlaveCierre();}
					case -22:
						break;
					case 22:
						{return ops.unidadInterrogacionCierre();}
					case -23:
						break;
					case 23:
						{return ops.unidadExclamacionCierre();}
					case -24:
						break;
					case 24:
						{return ops.unidadAsignacion();}
					case -25:
						break;
					case 25:
						{return ops.unidadIgual();}
					case -26:
						break;
					case 26:
						{return ops.unidadDisyuncion();}
					case -27:
						break;
					case 27:
						{return ops.unidadReal();}
					case -28:
						break;
					case 28:
						{return ops.unidadMenorIgual();}
					case -29:
						break;
					case 29:
						{return ops.unidadMayorIgual();}
					case -30:
						break;
					case 30:
						{return ops.unidadDistinto();}
					case -31:
						break;
					case 31:
						{return ops.unidadImprimir();}
					case -32:
						break;
					case 32:
						{return ops.unidadInt();}
					case -33:
						break;
					case 33:
						{return ops.unidadNegacion();}
					case -34:
						break;
					case 34:
						{return ops.unidadConjuncion();}
					case -35:
						break;
					case 35:
						{return ops.unidadCaracter();}
					case -36:
						break;
					case 36:
						{return ops.unidadBool();}
					case -37:
						break;
					case 37:
						{return ops.unidadEnum();}
					case -38:
						break;
					case 38:
						{return ops.unidadChar();}
					case -39:
						break;
					case 39:
						{}
					case -40:
						break;
					case 40:
						{return ops.unidadTrue();}
					case -41:
						break;
					case 41:
						{return ops.unidadVoid();}
					case -42:
						break;
					case 42:
						{return ops.unidadFloat();}
					case -43:
						break;
					case 43:
						{return ops.unidadFalse();}
					case -44:
						break;
					case 44:
						{return ops.unidadStruct();}
					case -45:
						break;
					case 45:
						{return ops.unidadObjects();}
					case -46:
						break;
					case 46:
						{return ops.unidadProcess();}
					case -47:
						break;
					case 48:
						{return ops.unidadIdentificador();}
					case -48:
						break;
					case 49:
						{ops.error();}
					case -49:
						break;
					case 50:
						{return ops.unidadEntero();}
					case -50:
						break;
					case 52:
						{return ops.unidadIdentificador();}
					case -51:
						break;
					case 53:
						{ops.error();}
					case -52:
						break;
					case 55:
						{return ops.unidadIdentificador();}
					case -53:
						break;
					case 56:
						{return ops.unidadIdentificador();}
					case -54:
						break;
					case 57:
						{return ops.unidadIdentificador();}
					case -55:
						break;
					case 58:
						{return ops.unidadIdentificador();}
					case -56:
						break;
					case 59:
						{return ops.unidadIdentificador();}
					case -57:
						break;
					case 60:
						{return ops.unidadIdentificador();}
					case -58:
						break;
					case 61:
						{return ops.unidadIdentificador();}
					case -59:
						break;
					case 62:
						{return ops.unidadIdentificador();}
					case -60:
						break;
					case 63:
						{return ops.unidadIdentificador();}
					case -61:
						break;
					case 64:
						{return ops.unidadIdentificador();}
					case -62:
						break;
					case 65:
						{return ops.unidadIdentificador();}
					case -63:
						break;
					case 66:
						{return ops.unidadIdentificador();}
					case -64:
						break;
					case 67:
						{return ops.unidadIdentificador();}
					case -65:
						break;
					case 68:
						{return ops.unidadIdentificador();}
					case -66:
						break;
					case 69:
						{return ops.unidadIdentificador();}
					case -67:
						break;
					case 70:
						{return ops.unidadIdentificador();}
					case -68:
						break;
					case 71:
						{return ops.unidadIdentificador();}
					case -69:
						break;
					case 72:
						{return ops.unidadIdentificador();}
					case -70:
						break;
					case 73:
						{return ops.unidadIdentificador();}
					case -71:
						break;
					case 74:
						{return ops.unidadIdentificador();}
					case -72:
						break;
					case 75:
						{return ops.unidadIdentificador();}
					case -73:
						break;
					case 76:
						{return ops.unidadIdentificador();}
					case -74:
						break;
					case 77:
						{return ops.unidadIdentificador();}
					case -75:
						break;
					case 78:
						{return ops.unidadIdentificador();}
					case -76:
						break;
					case 79:
						{return ops.unidadIdentificador();}
					case -77:
						break;
					case 80:
						{return ops.unidadIdentificador();}
					case -78:
						break;
					case 81:
						{return ops.unidadIdentificador();}
					case -79:
						break;
					case 82:
						{return ops.unidadIdentificador();}
					case -80:
						break;
					case 83:
						{return ops.unidadIdentificador();}
					case -81:
						break;
					case 84:
						{return ops.unidadIdentificador();}
					case -82:
						break;
					case 85:
						{return ops.unidadIdentificador();}
					case -83:
						break;
					case 86:
						{return ops.unidadIdentificador();}
					case -84:
						break;
					case 87:
						{return ops.unidadIdentificador();}
					case -85:
						break;
					case 88:
						{return ops.unidadIdentificador();}
					case -86:
						break;
					case 89:
						{return ops.unidadIdentificador();}
					case -87:
						break;
					case 90:
						{return ops.unidadIdentificador();}
					case -88:
						break;
					case 91:
						{return ops.unidadIdentificador();}
					case -89:
						break;
					case 92:
						{return ops.unidadIdentificador();}
					case -90:
						break;
					case 93:
						{return ops.unidadIdentificador();}
					case -91:
						break;
					case 94:
						{return ops.unidadIdentificador();}
					case -92:
						break;
					case 95:
						{return ops.unidadIdentificador();}
					case -93:
						break;
					case 96:
						{return ops.unidadIdentificador();}
					case -94:
						break;
					case 97:
						{return ops.unidadIdentificador();}
					case -95:
						break;
					case 98:
						{return ops.unidadIdentificador();}
					case -96:
						break;
					case 99:
						{return ops.unidadIdentificador();}
					case -97:
						break;
					case 100:
						{return ops.unidadIdentificador();}
					case -98:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
