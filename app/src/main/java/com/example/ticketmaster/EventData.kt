package com.example.ticketmaster

import com.google.gson.annotations.SerializedName

data class EventData(
    @SerializedName("_embedded")
    val _embedded: Embedded,
    val page: Page
)

data class Embedded(
    val events: List<Event>
)

data class Page(
    val totalElements: Int
)

data class Event(

    val _embedded: Details,
    val name: String,     // _embedded.events.name
    val dates: Dates,
    val priceRanges: List<Price_Ranges>,
    val url: String,      // _embedded.events.url
    val images: List<Images>

)

data class Dates(
    val start: Start
)

data class Start(
    val localDate: String, // _embedded.events.dates.start.localDate
    val localTime: String  // _embedded.events.dates.start.localTime
)

data class Details(
    val images: List<Images>,
    val venues: List<Venues>
    //val images: List
)

data class Images(
    val url: String,        // _embedded.events.images[0].url
    val width: Int,
    val height: Int
)

data class Venues(
    val name: String,      // _embedded.events._embedded.venues[0].name
    val city: City,        // _embedded.events._embedded.venues[0].city.name
    val state: State,
    val address: Address
)

data class Address(
    val line1: String,    // _embedded.events._embedded.venues[0].address.line1
    val line2: String     // _embedded.events._embedded.venues[0].address.line2
)

data class City(
    val name: String
)

data class State(
    val name: String
)

data class Price_Ranges(
    val min: Float,
    val max: Float
)
/*

Per Event:
Event Name
Venue,
City,
Address,
Event Date,
Event Time,
Price Range

{
    "_embedded": {
        "events": [
            {
                "name": "Midgets With Attitude",
                "type": "event",
                "id": "Z7r9jZ1A7AoOI",
                "test": false,
                "url": "https://www.ticketmaster.com/event/Z7r9jZ1A7AoOI",
                "locale": "en-us",
                "images": [
                    {
                        "ratio": "16_9",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RECOMENDATION_16_9.jpg",
                        "width": 100,
                        "height": 56,
                        "fallback": true
                    },
                    {
                        "ratio": "4_3",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_CUSTOM.jpg",
                        "width": 305,
                        "height": 225,
                        "fallback": true
                    },
                    {
                        "ratio": "16_9",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RETINA_LANDSCAPE_16_9.jpg",
                        "width": 1136,
                        "height": 639,
                        "fallback": true
                    },
                    {
                        "ratio": "16_9",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_TABLET_LANDSCAPE_16_9.jpg",
                        "width": 1024,
                        "height": 576,
                        "fallback": true
                    },
                    {
                        "ratio": "16_9",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RETINA_PORTRAIT_16_9.jpg",
                        "width": 640,
                        "height": 360,
                        "fallback": true
                    },
                    {
                        "ratio": "16_9",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_TABLET_LANDSCAPE_LARGE_16_9.jpg",
                        "width": 2048,
                        "height": 1152,
                        "fallback": true
                    },
                    {
                        "ratio": "16_9",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_EVENT_DETAIL_PAGE_16_9.jpg",
                        "width": 205,
                        "height": 115,
                        "fallback": true
                    },
                    {
                        "ratio": "3_2",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RETINA_PORTRAIT_3_2.jpg",
                        "width": 640,
                        "height": 427,
                        "fallback": true
                    },
                    {
                        "ratio": "3_2",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_TABLET_LANDSCAPE_3_2.jpg",
                        "width": 1024,
                        "height": 683,
                        "fallback": true
                    },
                    {
                        "ratio": "3_2",
                        "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_ARTIST_PAGE_3_2.jpg",
                        "width": 305,
                        "height": 203,
                        "fallback": true
                    }
                ],
                "sales": {
                    "public": {
                        "startDateTime": "1900-01-01T06:00:00Z",
                        "startTBD": false,
                        "startTBA": false,
                        "endDateTime": "2024-06-08T23:30:00Z"
                    }
                },
                "dates": {
                    "start": {
                        "localDate": "2024-06-08",
                        "localTime": "19:30:00",
                        "dateTime": "2024-06-08T23:30:00Z",
                        "dateTBD": false,
                        "dateTBA": false,
                        "timeTBA": false,
                        "noSpecificTime": false
                    },
                    "status": {
                        "code": "onsale"
                    },
                    "spanMultipleDays": false
                },
                "classifications": [
                    {
                        "primary": true,
                        "segment": {
                            "id": "KZFzniwnSyZfZ7v7nE",
                            "name": "Sports"
                        },
                        "genre": {
                            "id": "KnvZfZ7vA7A",
                            "name": "Miscellaneous"
                        },
                        "subGenre": {
                            "id": "KZazBEonSMnZfZ7vFIt",
                            "name": "Miscellaneous"
                        },
                        "family": false
                    }
                ],
                "outlets": [
                    {
                        "url": "https://www.ticketmaster.com/midgets-with-attitude-hartford-connecticut-06-08-2024/event/Zu0FM1R0e5F5V_K",
                        "type": "tmMarketPlace"
                    }
                ],
                "seatmap": {
                    "staticUrl": "https://content.resale.ticketmaster.com/maps/3054-1-1-main.gif"
                },
                "ticketing": {
                    "allInclusivePricing": {
                        "enabled": true
                    }
                },
                "_links": {
                    "self": {
                        "href": "/discovery/v2/events/Z7r9jZ1A7AoOI?locale=en-us"
                    },
                    "attractions": [
                        {
                            "href": "/discovery/v2/attractions/Z7r9jZaGqq?locale=en-us"
                        }
                    ],
                    "venues": [
                        {
                            "href": "/discovery/v2/venues/ZFr9jZ7vkA?locale=en-us"
                        }
                    ]
                },
                "_embedded": {
                    "venues": [
                        {
                            "name": "Webster Theatre",
                            "type": "venue",
                            "id": "ZFr9jZ7vkA",
                            "test": false,
                            "locale": "en-us",
                            "postalCode": "06114",
                            "timezone": "America/New_York",
                            "city": {
                                "name": "Hartford"
                            },
                            "state": {
                                "name": "Connecticut",
                                "stateCode": "CT"
                            },
                            "country": {
                                "name": "United States Of America",
                                "countryCode": "US"
                            },
                            "address": {
                                "line1": "31 Webster St",
                                "line2": "Hartford, CT"
                            },
                            "location": {
                                "longitude": "-72.670303",
                                "latitude": "41.739101"
                            },
                            "dmas": [
                                {
                                    "id": 296
                                }
                            ],
                            "upcomingEvents": {
                                "tmr": 9,
                                "_total": 9,
                                "_filtered": 0
                            },
                            "_links": {
                                "self": {
                                    "href": "/discovery/v2/venues/ZFr9jZ7vkA?locale=en-us"
                                }
                            }
                        }
                    ],
                    "attractions": [
                        {
                            "name": "Midgets With Attitude",
                            "type": "attraction",
                            "id": "Z7r9jZaGqq",
                            "test": false,
                            "locale": "en-us",
                            "images": [
                                {
                                    "ratio": "16_9",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RECOMENDATION_16_9.jpg",
                                    "width": 100,
                                    "height": 56,
                                    "fallback": true
                                },
                                {
                                    "ratio": "4_3",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_CUSTOM.jpg",
                                    "width": 305,
                                    "height": 225,
                                    "fallback": true
                                },
                                {
                                    "ratio": "16_9",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RETINA_LANDSCAPE_16_9.jpg",
                                    "width": 1136,
                                    "height": 639,
                                    "fallback": true
                                },
                                {
                                    "ratio": "16_9",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_TABLET_LANDSCAPE_16_9.jpg",
                                    "width": 1024,
                                    "height": 576,
                                    "fallback": true
                                },
                                {
                                    "ratio": "16_9",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RETINA_PORTRAIT_16_9.jpg",
                                    "width": 640,
                                    "height": 360,
                                    "fallback": true
                                },
                                {
                                    "ratio": "16_9",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_TABLET_LANDSCAPE_LARGE_16_9.jpg",
                                    "width": 2048,
                                    "height": 1152,
                                    "fallback": true
                                },
                                {
                                    "ratio": "16_9",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_EVENT_DETAIL_PAGE_16_9.jpg",
                                    "width": 205,
                                    "height": 115,
                                    "fallback": true
                                },
                                {
                                    "ratio": "3_2",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_RETINA_PORTRAIT_3_2.jpg",
                                    "width": 640,
                                    "height": 427,
                                    "fallback": true
                                },
                                {
                                    "ratio": "3_2",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_TABLET_LANDSCAPE_3_2.jpg",
                                    "width": 1024,
                                    "height": 683,
                                    "fallback": true
                                },
                                {
                                    "ratio": "3_2",
                                    "url": "https://s1.ticketm.net/dam/c/093/c74cfd95-af21-4e64-9f85-47677b951093_105651_ARTIST_PAGE_3_2.jpg",
                                    "width": 305,
                                    "height": 203,
                                    "fallback": true
                                }
                            ],
                            "classifications": [
                                {
                                    "primary": true,
                                    "segment": {
                                        "id": "KZFzniwnSyZfZ7v7nE",
                                        "name": "Sports"
                                    },
                                    "genre": {
                                        "id": "KnvZfZ7vA7A",
                                        "name": "Miscellaneous"
                                    },
                                    "subGenre": {
                                        "id": "KZazBEonSMnZfZ7vFIt",
                                        "name": "Miscellaneous"
                                    },
                                    "family": false
                                }
                            ],
                            "upcomingEvents": {
                                "tmr": 1,
                                "_total": 1,
                                "_filtered": 0
                            },
                            "_links": {
                                "self": {
                                    "href": "/discovery/v2/attractions/Z7r9jZaGqq?locale=en-us"
                                }
                            }
                        }
                    ]
                }
            }
        ]
    },
    "_links": {
        "first": {
            "href": "/discovery/v2/events.json?city=hartford&keyword=sports&page=0&size=1"
        },
        "self": {
            "href": "/discovery/v2/events.json?size=1&city=hartford&keyword=sports"
        },
        "next": {
            "href": "/discovery/v2/events.json?city=hartford&keyword=sports&page=1&size=1"
        },
        "last": {
            "href": "/discovery/v2/events.json?city=hartford&keyword=sports&page=92&size=1"
        }
    },
    "page": {
        "size": 1,
        "totalElements": 93,
        "totalPages": 93,
        "number": 0
    }
}
 */
