package com.cr1stal423.cards.service.impl;

import com.cr1stal423.cards.constants.CardsConstants;
import com.cr1stal423.cards.dto.CardsDto;
import com.cr1stal423.cards.entity.Cards;
import com.cr1stal423.cards.exception.CardAlreadyExistException;
import com.cr1stal423.cards.exception.ResourceNotFoundException;
import com.cr1stal423.cards.mapper.CardsMapper;
import com.cr1stal423.cards.repository.CardsRepository;
import com.cr1stal423.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;
    
    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findCardsByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new CardAlreadyExistException("Card already registered with given mobile number");
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }
    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setMobileNumber(mobileNumber);
        newCard.setAmountUsed(0);
        return newCard;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards optionalCards = cardsRepository.findCardsByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
        );
        return CardsMapper.mapToCardsDto(optionalCards,new CardsDto());
    }
    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        boolean isUpdated = true;
        Cards optionalCard = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card","cardNumber", cardsDto.getCardNumber())
        );
        Cards cards = CardsMapper.mapToCards(cardsDto,optionalCard);
        cardsRepository.save(cards);
        return isUpdated;
    }
    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        boolean isDeleted = true;
        Cards optionalCard = cardsRepository.findCardsByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber", mobileNumber)
        );
        cardsRepository.delete(optionalCard);
        return isDeleted;
    }
}
