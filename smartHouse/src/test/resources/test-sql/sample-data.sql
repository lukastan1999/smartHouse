-- Insert sample accommodations into the accommodation table
INSERT INTO accommodation (id, title, description, location, amenities, min_guest, max_guest, price, available, taken)
VALUES
    (1, 'Sample Accommodation 1', 'A cozy place to stay', 'Location 1', 'Wi-Fi, TV', 1, 4, 150.0, ARRAY['2024-09-06', '2024-09-07']::date[], ARRAY[]::date[]),
    (2, 'Sample Accommodation 2', 'Spacious and comfortable', 'Location 2', 'Pool, Gym', 2, 6, 250.0, ARRAY['2024-09-08', '2024-09-09']::date[], ARRAY[]::date[]);
