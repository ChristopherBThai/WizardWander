package main.util;

import java.util.ArrayList;

import entity.hostile.HostileEntity;
import entity.item.ItemEntity;
import entity.particle.Particle;
import entity.projectile.AbsProjectile;
import environment.block.Block;

public class EntityHolder {
	static ArrayList<HostileEntity> e = new ArrayList<HostileEntity>();;
	static ArrayList<Block> b = new ArrayList<Block>();
	static ArrayList<ItemEntity> s = new ArrayList<ItemEntity>();
	static ArrayList<AbsProjectile> p = new ArrayList<AbsProjectile>();
	static ArrayList<AbsProjectile> ep = new ArrayList<AbsProjectile>();
	static ArrayList<Particle> pa = new ArrayList<Particle>();
	
	public ArrayList<Particle> setPA(ArrayList<Particle> pa){
		this.pa = pa;
		return this.pa;
	}
	public ArrayList<Particle> getPA(){
		return this.pa;
	}
	public ArrayList<Particle> addPA(Particle pa){
		this.pa.add(pa);
		return this.pa;
	}
	public ArrayList<AbsProjectile> setEP(ArrayList<AbsProjectile> ep){
		this.ep = ep;
		return this.ep;
	}
	public ArrayList<AbsProjectile> getEP(){
		return this.ep;
	}
	public ArrayList<AbsProjectile> addEP(AbsProjectile ep){
		this.ep.add(ep);
		return this.ep;
	}
	public ArrayList<AbsProjectile> setP(ArrayList<AbsProjectile> p){
		this.p = p;
		return this.p;
	}
	public ArrayList<AbsProjectile> getP(){
		return this.p;
	}
	public ArrayList<AbsProjectile> addP(AbsProjectile p){
		this.p.add(p);
		return this.p;
	}
	public ArrayList<HostileEntity> setE(ArrayList<HostileEntity> e){
		this.e = e;
		return this.e;
	}
	public ArrayList<HostileEntity> getE(){
		return this.e;
	}
	public ArrayList<HostileEntity> addE(HostileEntity e){
		this.e.add(e);
		return this.e;
	}
	
	public ArrayList<Block> setB(ArrayList<Block> b){
		this.b = b;
		return this.b;
	}
	public ArrayList<Block> getB(){
		return this.b;
	}
	public ArrayList<Block> addB(Block b){
		this.b.add(b);
		return this.b;
	}
	
	public ArrayList<ItemEntity> setS(ArrayList<ItemEntity> s){
		this.s = s;
		return this.s;
	}
	public ArrayList<ItemEntity> getS(){
		return this.s;
	}
	public ArrayList<ItemEntity> addS(ItemEntity s){
		this.s.add(s);
		return this.s;
	}
	
	
}
