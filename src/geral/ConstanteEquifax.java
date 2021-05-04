package geral;


public enum ConstanteEquifax
{
	_0{
			public String[] getImpostos()
			{
				//0 -ICMS
				//1 -IPI
				String[] tipo ={"SIM","NAO"};
				return tipo;
			}
	},
	
	_1{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","SIM"};
			return tipo;
		}
	},
	
	_2{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","SIM"};
			return tipo;
		}
	},
	
	_3{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","SIM"};
			return tipo;
		}
	},
	
	_4{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","SIM"};
			return tipo;
		}
	},
	
	_5{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","SIM"};
			return tipo;
		}
	},
	
	_6{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","SIM"};
			return tipo;
		}
	},
	
	_7{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"NAO","NAO"};
			return tipo;
		}
	},
	
	_8{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"SIM","NAO"};
			return tipo;
		}
	},
	
	_9{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"NAO","NAO"};
			return tipo;
		}
	},
	
	_10{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"NAO","NAO"};
			return tipo;
		}
	},
	
	_99{
		public String[] getImpostos()
		{
			//0 -ICMS
			//1 -IPI
			String[] tipo ={"NAO","NAO"};
			return tipo;
		}
	}
	
	
	;
	
	
	private int size;
	private String name;
	
	ConstanteEquifax()
	{
	}
	
	ConstanteEquifax(int size,String name)
	{
		this.size = size;
		this.name = name;
	}

	public int getSize() 
	{
		return size;
	}

	public void setSize(int size) 
	{
		this.size = size;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	public abstract  String[]	getImpostos();
}
