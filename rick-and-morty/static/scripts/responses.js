function getBotResponse(input) {
    // //rock paper scissors
    // if (input == "rock") {
    //     return "paper";
    // } else if (input == "paper") {
    //     return "scissors";
    // } else if (input == "scissors") {
    //     return "rock";
    // }

    if (input) {
        switch(Math.ceil(Math.random() * 30)) {
            case 1:
                return "The Universe Is Basically An Animal...";
            case 2:
                return "...To Live Is To Risk It All...";
            case 3:
                return "Wubba Lubba Dub Dub!";
            case 4:
                return "This One's Not Directed By Ron Howard...";
            case 5:
                return "Sometimes Science Is More Art Than Science, Morty";
            case 6:
                return "Get Out Of Here, Summer! You Ruined The Season 4 Premiere";
            case 7:
                return "Hot Funeral Selfies...";
            case 8:
                return "I'm A Scientist; Because I Invent...";
            case 9:
                return "...Welcome To The Club, Pal.";
            case 10:
                return "I Always Slay It, Queen.";
            case 11:
                return "Weddings Are Basically Funerals With A Cake";
            case 12:
                return "...Meeting Them Head-On, Charging Into 'Em Like A Bull - That's How We Grow As People";
            case 13:
                return "What People Call Love Is Just A Chemical Reaction...";
            case 14:
                return "What, So Everyone’s Supposed To Sleep Every Single Night Now?";
            case 15:
                return "Hey, Muchacho, Does Your Planet Have Wiper Fluid Yet, Or Are You Going To Freak Out And Start Worshipping Us?";
            case 16:
                return "I Don't Sequel. It's Called Integrity.";
            case 17:
                return "I Wouldn't Lie To You. Well...That's A Lie. Huh.";
            case 18:
                return "Yeah Sure, I Mean If You Spend All Day Shuffling Words Around You Can Make Anything Sound Bad, Morty.";
            case 19:
                return "I'm Not A Beaver Who Believes In Jesus Christ, Morty...But Yeah It's Pretty Much A Narnia Thing.";            
            case 20:
                return "I Don't Like Being Told Where To Go Or What To Do. I Consider It A Violation. Did You Get All Those Seeds Up Your Butt?";
            case 21:
                return "That's Right, bitch It's The Prestige! You Prestiged Yourself!";
            case 22:
                return "There's A Lesson Here...And I'm Not Gonna Be The One To Figure It Out.";
            case 23:
                return "I Don't Discuss Problems, I Incinerate Them.";
            case 24:
                return "But That's Just It: I'm Not A Man. I'm God. You're Just Made In My Image.";
            case 25:
                return "You Never Follow Hell Demons To A Second Location. It's Always Hell.";
            case 26:
                return "Morty, Are You Gonna Be A Fucking America Nerd Or Are You Gonna Be Cool And Steal The Constitution With Grandpa?";
            case 27:
                return "For A Sec You Kinda Made Me Like Myself.";
            case 28:
                return "No, Morty. Cuz You Were Too Afraid To Tell Me. What We Had Was Abusive, Don't You See? I'm A Bad Partner Cuz I Never Made You A True Partner.";
            case 29:
                return "Fine. I Could Eat. But The Second He Reveals He's Evil, We're Gone.";
            case 30:
                return "Boom! Big reveal! I turned myself into a pickle!";
            default:
                "What is your ploblem?"
        }
    }
}