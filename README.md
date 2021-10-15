PRGF1_DU1
0
Zadání: 


Rasterizace úsečky, tečkované čáry a n-úhelníku

_ 1:  Vytvořte program pro kreslení úsečky zadané dvěma libovolnými koncovými body [x1,y1] a [x2,y2].

_ 2:  Koncové body zadávejte interaktivně pomocí tzv. pružné čáry. Stisknutím tlačítka myši zadáte první vrchol, při tažení se bude vykreslovat aktuální úsečka společně s již vykreslenou scébnou a při uvolnění tlačítka se potvrdí koncový vrchol.

_ 3:  K implementaci použijte třídy Point, Line a LineRasterizer. Třídy případně upravte nebo doplňte o potřebné metody. Návrhy tříd naleznete v modulu task1 (viz Oliva-obsah-ukázky a návody).

_ 4:  Vytvořte třídu FilledLineRasterizer dědící z abstraktní třídy LineRasterizer a správně implementující libovolný algoritmus pro rasterizaci úsečky. Do komentáře zapište, o jaký algoritmus se jedná, jeho výhody a nevýhody, případně jiná jeho specifika.

_ 5:  Prozkoumejte třídy Canvas, CanvasKey, CanvasPaint, …, CanvasRasterBufferedImage (v BB ukázky a návody), řešící hlavní aplikační třídu a UI. Soustřeďte se na interface Raster a třídu RasterBufferedImage a použijte ji pro vaše řešení.

_ 6:  Třídy zakomponujte do aplikace, která bude interaktivně zadávat body tvořící vrcholy n-úhelníku. Využijte ukázku CanvasRasterBufferedImage.

_ 7:  Zkušenější studenti mohou použít aplikační logiku z modulu task2, primárně určeného pro druhou úlohu. Tyto ukázky aplikačního řešení nejsou dogma, můžete je modifikovat nebo navrhnout vlastní. Snažte se o rozdělení aplikace na smysluplné třídy a zachovejte koncept rozhraní a tříd Raster, Point, Line a LineRasterizer.

_ 8:  Vytvořte si vhodnou třídu Polygon pro ukládání vrcholů. Je vhodnější ukládat vrcholy ne hrany z důvodu zajištění uzavřenosti útvaru, viz druhá úloha.

_ 9:  Vrcholy zadávejte interaktivně: stisknutím tlačítka myši vytvořte nový bod spojený s dvěma již vytvořenými vrcholy, např. s prvním a posledním. Tažením kreslete pružnou čáru k oběma vrcholům a uvolněním tlačítka přidejte bod do seznamu vrcholů n-úhelníku.

_ 10: Přidejte režim (po stisknutí klávesy T) pro kreslení pravoúhlého trojúhelníka, první dva body tvoří přeponu, třetí leží Thaletově kružnici.

_ 11: Přidejte třídu DottedLineRasterizer dědící také z abstraktní třídy LineRasterizer, vykreslující tečkovanou úsečku. Zakomponujte třídu do aplikace, například při editaci se bude vykreslovat tečkovaná čára a překreslení celého útvaru bude plnou čarou.

_ 12: Implementujte funkci na klávesu C pro mazání plátna a všech datových struktur.

Bonus:

1:  Bonus: Upravte program tak, aby bylo možné souřadnice jednotlivých vrcholů editovat myší. Například při stisku pravého tlačítka myši naleznete nejbližší vrchol a tažením mu nastavíte novou souřadnici. Podobně i přidávání nových vrcholů bude nový vrchol umístěn do nejbližší hrany.

2:  Bonus2: u tečkované úsečky modifikujte algoritmus tak, aby tečky byly vzdáleny stejně daleko, bez ohledu na strmost čáry, vzdálenost teček je vhodné parametrizovat. První a poslední mezeru mezi tečkami upravte tak, aby byly stejné, ostatní podle parametru. Zkuste navrhnout řešení pro různé typy čar, čárkovanou, tečkovanou, čerchovanou, …

Odevzdání:

1:  Při hodnocení je kladen důraz na funkčnost programu pro libovolně zadané koncové body, na přesnost vykreslení a na kvalitu návrhu a čitelnost kódu. Kód vhodně rozdělte do rozhraní a tříd. Kód očistěte od ladicích či pokusných nefunkčních částí.

2:  Odevzdávejte prostřednictvím BB (Olivy), před odevzdáním si znovu přečtěte pravidla odevzdávání semestrálních projektů a průběžných úloh.

3:  Zkušenější studenti si mohou vytvořit GITový repozitář a pravidelně commitovat postup.
