package model.teste;

class Mammal
{
	String name="fury ";
	
	String makeNoise()
	{
		return "generic noise ";
	}
}

class Zebra extends Mammal
{
	String name="Stripes ";
	String makeNoise()
	{
		return "bray ";
	}
}


