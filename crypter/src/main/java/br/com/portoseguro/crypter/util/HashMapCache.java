package br.com.portoseguro.crypter.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HashMapCache {

	private static final int MAX = 100000;
	private static final Map<String, String> CACHE = Collections.synchronizedMap(new LinkedHashMap<String, String>(MAX) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
			return size() > MAX;
		}
	});
	
	public static String get(String key) {
		return CACHE.get(key);
	}
	
	public static String put(String key, String value) {
		return CACHE.put(key, value);
	}
	
	public static boolean containsKey(String key) {
		return CACHE.containsKey(key);
	}
	
	public static int size() {
		return CACHE.size();
	}
	
	public static void main(String[] args) {
		Set<Pessoa> pessoas = new HashSet<>();
		Pessoa pessoa = new Pessoa("Hallan", 28);
		Pessoa pessoa2 = new Pessoa("Hallan", 28);
		Pessoa p3 = new Pessoa("Fabiano", 30);
		System.out.println(pessoa.hashCode());
		System.out.println(pessoa2.hashCode());
		System.out.println(p3.hashCode());
		pessoas.add(pessoa);
		pessoas.add(pessoa2);
		pessoas.add(p3);
		System.out.println(pessoas.size());
	}
}

class Pessoa {
	Pessoa(String nome, Integer idade){
		super();
		this.nome = nome;
		this.idade = idade;
	}
	
	String nome;
	Integer idade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}