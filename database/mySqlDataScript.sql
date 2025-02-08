INSERT INTO classifiers(title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier');

SET @id = LAST_INSERT_ID();

INSERT INTO classifier_values(classifier_id, ic, description)
VALUES (@id, 'TRAVEL_MEDICAL', 'Covers medical expenses during travel'),
       (@id, 'TRAVEL_CANCELLATION', 'Covers trip cancellation costs'),
       (@id, 'TRAVEL_LOSS_BAGGAGE', 'Covers loss or damage to baggage'),
       (@id, 'TRAVEL_THIRD_PARTY_LIABILITY', 'Covers damages caused to third parties'),
       (@id, 'TRAVEL_EVACUATION', 'Covers emergency evacuation costs'),
       (@id, 'TRAVEL_SPORT_ACTIVITIES', 'Covers risks related to sport activities during travel');



