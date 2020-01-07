# Exercise 5: Diary Study & Sensory Substitution

Author: Nils Verheyen\
Matriculation number: 3043171

## A) Diary Study

Normally, it is possible to see exactly when a user is in his apartment/house if it is assumed that the user starts the day at his place of residence. This is due to the different context changes. For example, if the user does not change the location on foot and has free access to the new location, the means of transport used is the car, bicycle or public transport. In any case, authentication, e.g. with a key or ticket (have) is necessary. This makes it possible to recognise when the user leaves the place of residence. If several days are recorded, exact movement profiles and habits of the users can be created.
An exact determination of the payment method at different locations can be made, depending on what the user specifies in his context.

By inserting fake data in the record, which can be removed later, anonymisation is possible to a limited extent. Enumerations such as the type, which are fixed from the outset, can be obfuscated by numerical values. The data on context and device should be abstracted as far as possible so that a meaningful evaluation is still possible. Payments by credit card can, for example, be recorded under the context "Shop". It may not be necessary for the evaluation to know in which type of shop the user authenticates himself.

## B) Lifelogging

### B1) Foods & Drinks

Ten events were recorded over the entire day where food and drinks were consumed. Both types were consumed at the three large meals. At the beginning of the day, coffee is a major component of food, but this changes during the course of the day. Energy-rich foods were consumed at almost all meals. Between the large meals, longer intervals were observed in which no solid food was consumed. The individual components of the meals can be important for an evaluation. The amount of salt and sugar used cannot be taken from prepared food. These data can be important for food manufacturers in the production and design of new food products. The frequency of the drinks alone allows conclusions to be drawn about possible habits and preferences. It is also possible to determine for that day whether the user has eaten a healthy diet or not. For a diary over several days a general (un)healthy diet can be determined. E.g. nutritionists can start here and recommend compensatory sports activities to the user.

### B2) Change in location

The change of location can be an indication of the user's daily exercise, which in turn has implications for the analysis of the previously established diet. If the exact addresses of the places and means of transport are known, the user's degree of movement during the day can be determined. In any case, the user's movement profiles must be made anonymous, as sensitive data are recorded in combination with a time of day. For example, has a user been at an event at a certain time. Investigations by authorities or companies are extremely facilitated by this data and the user becomes increasingly transparent.

## Authentification

Data is provided in human readable form followed by raw csv data separated by colons.

### Readable

| type |    datetime    |   device   |   context   |
| ---- | -------------- | ---------- | ----------- |
| know | 19.12.19 06:53 | mobile     | Living room |
| know | 19.12.19 06:54 | mobile     | Living room |
| know | 19.12.19 06:59 | mobile     | Living room |
| know | 19.12.19 07:05 | mobile     | Living room |
| have | 19.12.19 07:47 | door       | Garage      |
| have | 19.12.19 07:53 | bicycle    | Doctor      |
| are  | 19.12.19 07:58 | reception  | Doctor      |
| have | 19.12.19 07:59 | bicycle    | Doctor      |
| have | 19.12.19 08:04 | front door | Residence   |
| know | 19.12.19 08:27 | mobile     | Living room |
| have | 19.12.19 08:59 | bicycle    | Doctor      |
| are  | 19.12.19 09:00 | reception  | Doctor      |
| have | 19.12.19 09:22 | bicycle    | Doctor      |
| have | 19.12.19 09:31 | door       | Garage      |
| have | 19.12.19 09:31 | front door | Residence   |
| know | 19.12.19 09:42 | mobile     | Office      |
| know | 19.12.19 09:42 | pc         | Office      |
| know | 19.12.19 09:43 | pc         | Office      |
| know | 19.12.19 09:45 | mobile     | Office      |
| know | 19.12.19 09:54 | pc         | Office      |
| know | 19.12.19 10:05 | pc         | Office      |
| know | 19.12.19 10:20 | pc         | Office      |
| know | 19.12.19 10:21 | pc         | Office      |
| know | 19.12.19 10:21 | pc         | Office      |
| know | 19.12.19 10:49 | pc         | Office      |
| have | 19.12.19 12:02 | front door | Residence   |
| know | 19.12.19 12:11 | pc         | Office      |
| know | 19.12.19 14:42 | pc         | Office      |
| know | 19.12.19 15:32 | pc         | Office      |
| know | 19.12.19 15:32 | pc         | Office      |
| have | 19.12.19 16:16 | gate       | Garage      |
| have | 19.12.19 16:20 | gate       | Garage      |
| have | 19.12.19 16:21 | front door | Residence   |
| know | 19.12.19 16:25 | mobile     | Office      |
| know | 19.12.19 18:48 | mobile     | Office      |
| know | 19.12.19 19:26 | handheld   | Living room |
| know | 19.12.19 19:34 | mobile     | Living room |
| know | 19.12.19 20:15 | mobile     | Living room |
| know | 19.12.19 20:22 | mobile     | Living room |
| know | 19.12.19 21:44 | mobile     | Living room |

### CSV

Type,Date (Europe/Berlin MEZ),Device,Context\
know ,19.12.19 06:53,mobile,Living room\
know ,19.12.19 06:54,mobile,Living room\
know ,19.12.19 06:59,mobile,Living room\
know ,19.12.19 07:05,mobile,Living room\
have,19.12.19 07:47,door,Garage\
have,19.12.19 07:53,bicycle,Doctor\
are,19.12.19 07:58,reception,Doctor\
have,19.12.19 07:59,bicycle,Doctor\
have,19.12.19 08:04,front door,Residence\
know ,19.12.19 08:27,mobile,Living room\
have,19.12.19 08:59,bicycle,Doctor\
are,19.12.19 09:00,reception,Doctor\
have,19.12.19 09:22,bicycle,Doctor\
have,19.12.19 09:31,door,Garage\
have,19.12.19 09:31,front door,Residence\
know ,19.12.19 09:42,mobile,Office\
know ,19.12.19 09:42,pc,Office\
know ,19.12.19 09:43,pc,Office\
know ,19.12.19 09:45,mobile,Office\
know ,19.12.19 09:54,pc,Office\
know ,19.12.19 10:05,pc,Office\
know ,19.12.19 10:20,pc,Office\
know ,19.12.19 10:21,pc,Office\
know ,19.12.19 10:21,pc,Office\
know ,19.12.19 10:49,pc,Office\
have,19.12.19 12:02,front door,Residence\
know ,19.12.19 12:11,pc,Office\
know ,19.12.19 14:42,pc,Office\
know ,19.12.19 15:32,pc,Office\
know ,19.12.19 15:32,pc,Office\
have,19.12.19 16:16,gate,Garage\
have,19.12.19 16:20,gate,Garage\
have,19.12.19 16:21,front door,Residence\
know ,19.12.19 16:25,mobile,Office\
know ,19.12.19 18:48,mobile,Office\
know,19.12.19 19:26,handheld,Living room\
know ,19.12.19 19:34,mobile,Living room\
know ,19.12.19 20:15,mobile,Living room\
know ,19.12.19 20:22,mobile,Living room\
know ,19.12.19 21:44,mobile,Living room
