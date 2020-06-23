package com.maduro.poker.unit.handtypeviewer;

import com.maduro.poker.unit.handtype.HandTypeServiceDTO;

public class HandTypeViewerSevice {

	public void showResult(HandTypeServiceDTO handTypeServiceDTO) {
		System.out.println("total tied: " + handTypeServiceDTO.getTiedHandsMap().size());
		System.out.println("total best: " + handTypeServiceDTO.getBestHandsWinMap().size());
		System.out.println("total worst: " + handTypeServiceDTO.getWorstHandsWinMap().size());
	}

}
