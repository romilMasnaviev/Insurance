INSERT INTO classifiers(title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier');


INSERT INTO classifier_values(classifier_id, ic, description)
VALUES (1, 'TRAVEL_MEDICAL', 'Covers medical expenses during travel'),
       (1, 'TRAVEL_CANCELLATION', 'Covers trip cancellation costs'),
       (1, 'TRAVEL_LOSS_BAGGAGE', 'Covers loss or damage to baggage'),
       (1, 'TRAVEL_THIRD_PARTY_LIABILITY', 'Covers damages caused to third parties'),
       (1, 'TRAVEL_EVACUATION', 'Covers emergency evacuation costs'),
       (1, 'TRAVEL_SPORT_ACTIVITIES', 'Covers risks related to sport activities during travel');



