CREATE TABLE stores (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    subdomain VARCHAR(250) NOT NULL
);
