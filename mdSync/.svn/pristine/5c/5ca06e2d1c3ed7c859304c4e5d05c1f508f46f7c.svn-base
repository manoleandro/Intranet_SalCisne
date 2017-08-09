package com.mdSync.facade;

import com.mdSync.thread.SyncThread;

public class SyncFacade {
	
	private SyncThread syncThread;
	
    public SyncFacade() {
		syncThread = new SyncThread();
	}

	public void executar() {
    	Thread sync = new Thread(syncThread);
    	sync.start();
    }
}