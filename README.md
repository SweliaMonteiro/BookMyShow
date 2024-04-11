# Create Book My Show Application

## Problem Statement

As a part of book my show system, you need to allow users to LogIn or SignUp on the platform. You also need to expose a functionality using which theatre admins can create a show. Once this show has been created, users will be able to book tickets for this show on the platform.

## Requirements
#### User SignUp or LogIn
1. Allow a user to SignUp to the platform. Encrypt the password using BCrypt encryption technique.
2. For LogIn, authenticate the user by verifying the email and password.

#### Book a Ticket
1. The request to create a booking will contain the following things:
   * User ID - The ID of the user who wants to book a movie ticket.
   * Show ID - The ID of the show to be booked.
   * ShowSeatIds List - The list of seat IDs that the user wants to book.
2. The functionality should do basic data validation checks ex. the user should not be able to book a seat that is already booked.
3. Once this functionality executes successfully, we should store booking details in the database.
4. The user should be able to book multiple seats in a single booking.
5. The user should not be able to book a seat that is already booked.

#### Create a Show
1. The request to create a show will contain the following things:
    * Movie ID - The ID of the movie which is being shown.
    * User ID - The ID of the user who is creating the show.
    * Screen ID - The ID of the screen where the show is being hosted.
    * PricingConfig (List<Pair<SeatType, Double>) - The price of each seat type for this show.
    * Start Time - The start time of the show.
    * End Time - The end time of the show.
    * Date - The date on which the show is being hosted.
    * Feature List - The list of features that are supported by this show.
2. This functionality should be only accessible to the theatre admin.
3. Every screen has supported features like 2D, 3D, Dolby vision etc. The show that is going to be scheduled on a screen should support all or subset of these features. Example scenarios:
    * If a screen is a 2D screen, then a 3D show cannot be scheduled on it.
    * If a screen supports 3D, 2D, Dolby atmos, then a show which supports 2D and Dolby atmos can be scheduled on it.
4. The functionality should do basic data validation checks ex. the start time should be before the end time.
5. Once this functionality executes successfully, we should store show details, seats related details for this show and pricing details for this show in the database.
