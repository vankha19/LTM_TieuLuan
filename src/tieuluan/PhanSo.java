/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tieuluan;

/**
 *
 * @author Admin
 */
public class PhanSo {
	int TuSo;
	int MauSo;

	public PhanSo() {

	}

	public PhanSo(int TuSo, int MauSo) {
		this.TuSo = TuSo;
		this.MauSo = MauSo;
	}

	public int getTuSo() {
		return TuSo;
	}

	public void setTuSo(int TuSo) {
		this.TuSo = TuSo;
	}

	public int getMauSo() {
		return MauSo;
	}

	public void setMauSo(int MauSo) {
		this.MauSo = MauSo;
	}
 
	@Override
	public String toString() {
		return TuSo + "/" + MauSo;
		
	}

}
