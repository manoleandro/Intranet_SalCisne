package com.mdSync.thread;

import com.mdSync.bean.Sync;

public class SyncThread implements Runnable{

	private Sync sync;
	
	public SyncThread() {}

	private Sync getSyng() {
		if (this.sync == null) {
			this.sync = new Sync();
		}
		return this.sync;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("------------- Iniciando Sincronização-------------");
			this.getSyng().iniciar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- Processo Finalizado ---");
		System.exit(1);
	}
}