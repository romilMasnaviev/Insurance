INSERT INTO classifiers(title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier'),
       ('COUNTRY', 'Country classifier');

SET @risk_type_id_cl = (SELECT id AS rt
                        FROM classifiers
                        WHERE title = 'RISK_TYPE');
INSERT INTO classifier_values(classifier_id, ic, description)
VALUES (@risk_type_id_cl, 'TRAVEL_MEDICAL', 'Covers medical expenses during travel'),
       (@risk_type_id_cl, 'TRAVEL_CANCELLATION', 'Covers trip cancellation costs'),
       (@risk_type_id_cl, 'TRAVEL_LOSS_BAGGAGE', 'Covers loss or damage to baggage'),
       (@risk_type_id_cl, 'TRAVEL_THIRD_PARTY_LIABILITY', 'Covers damages caused to third parties'),
       (@risk_type_id_cl, 'TRAVEL_EVACUATION', 'Covers emergency evacuation costs'),
       (@risk_type_id_cl, 'TRAVEL_SPORT_ACTIVITIES', 'Covers risks related to sport activities during travel');


SET @country_id_cl = (SELECT id AS rt
                      FROM classifiers
                      WHERE title = 'RISK_TYPE');
INSERT INTO classifier_values(classifier_id, ic, description)
VALUES (@country_id_cl, 'LATVIA', 'Country Latvia'),
       (@country_id_cl, 'SPAIN', 'Country Spain'),
       (@country_id_cl, 'JAPAN', 'Country Japan');


INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES ('LATVIA',1.00),
       ('SPAIN',2.50),
       ('JAPAN',3.50)

