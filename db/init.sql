-- Enable UUID extension if needed
-- CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- 1) CATEGORIES

CREATE TABLE IF NOT EXISTS categories
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL
    );

-- 2) ITEM LOCATIONS

CREATE TABLE IF NOT EXISTS item_locations
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL
    );

-- 3) FRIDGE ITEMS

CREATE TABLE IF NOT EXISTS fridge_items
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(50)  NOT NULL,
    quantity    INT          NOT NULL CHECK (quantity >= 0),
    stored_at   TIMESTAMP    NOT NULL,
    best_before DATE         NOT NULL,
    location_id UUID         NOT NULL,
    deleted_at  TIMESTAMP,
    is_deleted  BOOLEAN      NOT NULL DEFAULT FALSE,
    category_id UUID         NOT NULL,
    CONSTRAINT fk_fridge_items_locations
    FOREIGN KEY (location_id) REFERENCES item_locations (id),
    CONSTRAINT fk_fridge_items_categories
    FOREIGN KEY (category_id) REFERENCES categories (id)
    );

-- 4) SEED PODACI

-- Categories
INSERT INTO categories (id, name) VALUES
                                      ('bc7d5b27-41f9-412f-a7a7-a6b78610c88d', 'Meats'),
                                      ('236a1e9b-349c-4d35-a438-eb76d59de5e7', 'Dairy')
    ON CONFLICT (id) DO NOTHING;

-- Item locations
INSERT INTO item_locations (id, name) VALUES
                                          ('c121fa5f-a2eb-481d-832e-f2e4346fc33d', 'Freezer'),
                                          ('c23403c6-f07b-48a6-9126-d966bde1882e', 'Fridge door')
    ON CONFLICT (id) DO NOTHING;

-- Fridge items (primeri)
INSERT INTO fridge_items (name, quantity, stored_at, best_before, location_id, category_id)
VALUES
    ('Pork chops', 2, now(), DATE '2026-01-10',
     'c121fa5f-a2eb-481d-832e-f2e4346fc33d', 'bc7d5b27-41f9-412f-a7a7-a6b78610c88d'),
    ('Milk', 1, now(), DATE '2026-02-01',
     'c23403c6-f07b-48a6-9126-d966bde1882e', '236a1e9b-349c-4d35-a438-eb76d59de5e7');
