package com.example.wineshop;

import org.springframework.data.jpa.repository.JpaRepository;

interface WineryRepository extends JpaRepository<Winery, Long> {
        Winery findByName(String name);
}
