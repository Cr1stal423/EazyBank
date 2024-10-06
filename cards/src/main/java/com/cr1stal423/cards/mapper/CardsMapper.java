package com.cr1stal423.cards.mapper;

import com.cr1stal423.cards.dto.CardsDto;
import com.cr1stal423.cards.entity.Cards;

public class CardsMapper {
    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto){
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards){
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        return cards;
    }
}
