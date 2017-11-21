package com.github.nguyentrucxinh.foodmenulist.api;

import com.github.nguyentrucxinh.foodmenulist.config.SecurityConstants;
import com.github.nguyentrucxinh.foodmenulist.domain.Card;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecurityConstants.API_USER_URL + "/cards")
public class CardController extends GenericControllerImpl<Card> {
}
