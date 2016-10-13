public class Parser {
	LexicalAnalyzer tknzr;
	//Constructor for parser using lexical analyzer
	public Parser(LexicalAnalyzer lex) {
		this.tknzr = lex;
		boolean result = isProgram();
		System.out.println("result" + " :: " + result);
	}
	//Recursively builds language and validates that it is a correct grammar.
	public boolean isProgram() {
		if (tknzr.getCurrToken().equals(Constants.PROCEDURE)) {
			tknzr.getNextToken();
			if (isVar()) {
				tknzr.getNextToken();
				if (tknzr.getCurrToken().equals(Constants.BEGIN)) {
					tknzr.setUsed();
					tknzr.goBackAll();
					if (isStmt_List()) {
						tknzr.setUsed();
						if (tknzr.getCurrToken().equals(Constants.END)) {
							tknzr.getNextToken();
							if (tknzr.getCurrToken().equals(Constants.SEMICOLON)) {
								tknzr.setUsed();
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	//returns statement list boolean value
	public boolean isStmt_List() {
		return isStmt_List(true);
	}
	public boolean isStmt_List(boolean isFirst) {
		if (isFirst && isStmt()) {
			// tknzr.getNextToken);
			return isStmt_List(false);
		} else if (isFirst) {
			return false;
		} else {
			if (isStmt()) {
				return isStmt_List(false);
			} else {
				tknzr.goBackAll();
				if (tknzr.invalid)
					return false;
				return true;
			}
		}

	}
	//checks valid statement token, calls isAssign()
	public boolean isStmt() {
		if (isAssign() || isIf()) {
			if (tknzr.invalid)
				return false;
			tknzr.goBackAll();
			return true;
		} else {
			return false;
		}
	}
	//checks for valid if statement token
	public boolean isIf() {
		if (tknzr.getCurrToken().equals(Constants.IF)) {
			tknzr.getNextToken();
			if (tknzr.getCurrToken().equals(Constants.LPAREN)) {
				tknzr.getNextToken();
				if (isBool()) {
					tknzr.getNextToken();
					if (tknzr.getCurrToken().equals(Constants.RPAREN)) {
						tknzr.getNextToken();
						if (tknzr.getCurrToken().equals(Constants.THEN)) {
							tknzr.setUsed();
							tknzr.getNextToken();
							if (isStmt_List(true)) {
								// tknzr.getNextToken();
								if (tknzr.getCurrToken().equals(Constants.ENDIF)) {
									tknzr.getNextToken();
									if (tknzr.getCurrToken().equals(Constants.SEMICOLON)) {
										tknzr.setUsed();
										tknzr.goBackAll();
										return true;
									} else {
										tknzr.goBackAll();
										tknzr.invalid = true;
										return false;
									}
								} else if (tknzr.getCurrToken().equals(Constants.ELSE)) {
									tknzr.getNextToken();
									if (isStmt_List(true)) {
										tknzr.goBackAll();
										if (tknzr.getCurrToken().equals(Constants.ENDIF)) {
											tknzr.getNextToken();
											if (tknzr.getCurrToken().equals(Constants.SEMICOLON)) {
												tknzr.setUsed();
												tknzr.goBackAll();
												return true;
											} else {
												tknzr.goBackAll();
												tknzr.invalid = true;
												return false;
											}
										} else {
											tknzr.invalid = true;
											tknzr.goBackAll();
											return false;
										}
									} else {
										tknzr.invalid = true;
										tknzr.goBackAll();
										return false;
									}
								} else {
									tknzr.invalid = true;
									tknzr.goBackAll();
									return false;
								}
							} else {
								tknzr.invalid = true;
								tknzr.goBackAll();
								return false;
							}
						} else {
							tknzr.invalid = true;
							tknzr.goBackAll();
							return false;
						}
					} else {
						tknzr.goBackAll();
						return false;
					}
				} else {
					tknzr.goBackAll();
					return false;
				}
			} else {
				tknzr.goBackAll();
				return false;
			}
		} else {
			tknzr.goBackAll();
			return false;
		}
	}
	//checks for valid assignment token
	public boolean isAssign() {
		if (isVar()) {
			tknzr.getNextToken();
			if (tknzr.getCurrToken().equals(Constants.ASSIGNMENT)) {
				tknzr.getNextToken();
				if (isExpr()) {
					tknzr.getNextToken();
					if (tknzr.getCurrToken().equals(Constants.SEMICOLON)) {
						tknzr.setUsed();
						return true;
					} else {
						tknzr.goBackAll();
						return false;
					}
				} else {
					tknzr.goBackAll();
					return false;
				}
			} else {
				tknzr.goBackAll();
				return false;
			}
		} else {
			tknzr.goBackAll();
			return false;
		}
	}
	//checks if it is a valid expression token, possible recursively
	public boolean isExpr() {
		if (isTerm()) {
			tknzr.getNextToken();
			if (tknzr.getCurrToken().equals(Constants.ADDITION) || tknzr.getCurrToken().equals(Constants.SUBTRACTION) || tknzr.getCurrToken().equals(Constants.DIVISION)
					|| tknzr.getCurrToken().equals(Constants.MULTIPLICATION)) {
				tknzr.getNextToken();
				if (isExpr()) {
					return true;
				} else {
					tknzr.goBackAll();
					return false;
				}
			} else {
				tknzr.goBackOne();
				return true;
			}
		} else {
			tknzr.goBackAll();
			return false;
		}
	}
	//checks if it a valid term token
	public boolean isTerm() {
		if (isVar() || isInt()) {
			return true;
		} else {
			return false;
		}
	}
	//checks for valid boolean token
	public boolean isBool() {
		if (isVar()) {
			String token = tknzr.getNextToken();

			if (token.equals(Constants.EQUAL) || token.equals(Constants.NEQUAL)) {
				tknzr.getNextToken();
				if (isInt()) {
					tknzr.setUsed();
					return true;
				} else {
					tknzr.goBackAll();
					return false;
				}
			} else {
				tknzr.goBackAll();
				return false;
			}
		} else {
			tknzr.goBackAll();
			return false;
		}
	}
	//checks for valid int token potentially recursively
	public boolean isInt() {
		if (tknzr.getCurrPosition() == 0 && isDigit()) {
			tknzr.getNextCharacter();
			return isInt();
		} else if (tknzr.getCurrPosition() > 0 && tknzr.isEndOfString()) {
			return true;
		} else if (tknzr.getCurrPosition() > 0 && isDigit()) {
			tknzr.getNextCharacter();
			return isInt();
		} else {
			return false;
		}
	}
	//checks for valid variable token potentially recursively
	public boolean isVar() {

		if (tknzr.getCurrPosition() == 0) {
			if (isLetter()) {
				tknzr.getNextCharacter();
				return isVar();
			} else {
				tknzr.resetCurrPosition();
				return false;
			}
		} else if (tknzr.getCurrPosition() > 0 && isLetterDigit()) {
			tknzr.getNextCharacter();
			return isVar();
		} else if (tknzr.isEndOfString()) {
			// tknzr.removeTokens();
			return true;
		} else {
			tknzr.resetCurrPosition();
			return false;
		}
	}
	//checks if either letter or digit token
	public boolean isLetterDigit() {
		if (isLetter() || isDigit()) {
			return true;
		} else {
			return false;
		}
	}
	//checks for valid alphabetical character token
	public boolean isLetter() {
		char c = tknzr.getCurrCharacter();
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
			return true;
		} else {
			return false;
		}
	}
	//checks for valid digit token
	public boolean isDigit() {
		char c = tknzr.getCurrCharacter();
		if (c >= '0' && c <= '9') {
			return true;
		} else {
			return false;
		}
	}
	//defined constants
	static class Constants {

		static final String SEMICOLON = ";";
		static final String ASSIGNMENT = ":=";
		static final String ADDITION = "+";
		static final String SUBTRACTION = "-";
		static final String MULTIPLICATION = "*";
		static final String DIVISION = "/";
		static final String BEGIN = "begin";
		static final String END = "end";
		static final String PROCEDURE = "procedure";
		static final String IF = "if";
		static final String ENDIF = "endif";
		static final String LPAREN = "(";
		static final String RPAREN = ")";
		static final String EQUAL = "=";
		static final String NEQUAL = "!=";
		static final String THEN = "then";
		static final String ELSE = "else";

	}

}