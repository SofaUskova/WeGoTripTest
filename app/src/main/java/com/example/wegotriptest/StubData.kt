package com.example.wegotriptest

import com.example.wegotriptest.presentation.models.Feedback
import java.util.*

object StubData {

    val stubFeedback = Feedback(
        id = UUID.randomUUID().toString(),
        tourRating = 0,
        guideRating = 3,
        infoRating = 2,
        navRating = 4,
        features = "Купили экскурсию по акционной цене, что очень порадовало. Оплатили на сайте, " +
                "через 20 минут на почту и телефон пришли ссылки на скачивание. При входе на " +
                "канатную дорогу никаких проблем не возникло, дали билеты. Рекомендую ребят за " +
                "оперативную работу.",
        wishes = "Первый раз бронировала на сайте, переживала - раньше только экскурсии были только " +
                "очные. Все прошло очень замечательно и во время прошла по билету. Собор просто " +
                "сказка, очень красивый! Посетите обязательно!"
    )

}
