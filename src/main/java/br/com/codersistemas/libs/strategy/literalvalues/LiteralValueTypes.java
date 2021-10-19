package br.com.codersistemas.libs.strategy.literalvalues;

public enum LiteralValueTypes {
	
	STRING("java.lang.String"){
		Strategy getStrategy() {
			return new StringStrategy();
		}
	}, 
	INTEGER("java.lang.Integer"){
		Strategy getStrategy() {
			return new IntegerStrategy();
		}
	}, 
	LONG("java.lang.Long"){
		Strategy getStrategy() {
			return new LongStrategy();
		}
	}, 
	FLOAT("java.lang.Float"){
		Strategy getStrategy() {
			return new FloatStrategy();
		}
	},
	DOUBLE("java.lang.Double"){
		Strategy getStrategy() {
			return new DoubleStrategy();
		}
	},
	DATE("java.util.Date"){
		Strategy getStrategy() {
			return new DateStrategy();
		}
	},
	ENUM("java.lang.Enum"){
		Strategy getStrategy() {
			return new EnumStrategy();
		}
	},
	SHORT("java.lang.Short"){
		Strategy getStrategy() {
			return new ShortStrategy();
		}
	},
	BOOLEAN("java.lang.Boolean"){
		Strategy getStrategy() {
			return new BooleanStrategy();
		}
	},
	BIG_DECIMAL("java.math.BigDecimal"){
		Strategy getStrategy() {
			return new BigDecimalStrategy();
		}
	},
	ARRAY_LIST("java.util.ArrayList"){
		Strategy getStrategy() {
			return new ArrayListStrategy();
		}
	};
	
	private String classe;

	LiteralValueTypes(String classe){
		this.classe = classe;
	}
	
	abstract Strategy getStrategy();

	public static LiteralValueTypes from(String param) {
		LiteralValueTypes[] values = values();
		for (LiteralValueTypes type : values) {
			if(type.getClasse().equals(param)) {
				return type;
			}
		}
		return null;
	}

	public String getClasse() {
		return classe;
	}

}
