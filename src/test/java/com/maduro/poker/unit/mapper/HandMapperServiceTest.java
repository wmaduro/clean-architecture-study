package com.maduro.poker.unit.mapper;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.maduro.poker.domain.HandDataModel;
import com.maduro.poker.unit.file.FileParserServiceDTO;
import com.maduro.poker.unit.mapper.util.HandMapperServiceUtils;

public class HandMapperServiceTest {

	@Test
	void mustProcessFileParserServiceDTOSuccessfully() throws Exception {

//		FileParserServiceDTO fileParserServiceDTO = HandMapperServiceUtils.getFileParserServiceDTOWithOneHand();
//		HandMapperServiceDTO handMapperServiceDTO = new HandMapperService().process(fileParserServiceDTO);
//
//		assertTrue(validateOutcome(fileParserServiceDTO, handMapperServiceDTO));

	}

	private boolean validateOutcome(FileParserServiceDTO fileParserServiceDTO,
			HandMapperServiceDTO handMapperServiceDTO) {

		String handCode = null;
		int fileParserServiceDTOHandCounter = 0;

		for (HandDataModel handDataModel : fileParserServiceDTO.getHandDataModelList()) {

			fileParserServiceDTOHandCounter++;
			if (handCode == null) {
				handCode = handDataModel.getHand();
				continue;
			}

			if (!handCode.contentEquals(handDataModel.getHand())) {
				if (handMapperServiceDTO.getHandDataModelMap().get(handCode) == null) {
					return false;
				}
				int handMapperServiceDTOValueCounter = handMapperServiceDTO.getHandDataModelMap().get(handCode).size();

				if (handMapperServiceDTOValueCounter != fileParserServiceDTOHandCounter) {
					return false;
				}
			}
		}

		if (handCode != null) {
			int handMapperServiceDTOValueCounter = handMapperServiceDTO.getHandDataModelMap().get(handCode).size();

			if (handMapperServiceDTOValueCounter != fileParserServiceDTOHandCounter) {
				return false;
			}
		}

		return true;
	}
}
