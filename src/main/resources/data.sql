-- roles
INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'USER');

-- default profile picture
INSERT INTO pictures(id, url, public_id)
VALUES (1, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636195985/profile-pic_u3hhvx.png',
        'profile-pic_u3hhvx');

INSERT INTO users (id, username, password, profile_picture_id, email, first_name, last_name)
VALUES (1, 'user', '33f2dded467875e89ed7011219645022db8bff22a15f0a485fc6e8d4c6dbc2ea6bd150cbd2fb9a7f', 1, 'user@gmail.com', 'User', 'User');

-- users roles
INSERT INTO users_roles (user_entity_id, roles_id)
VALUES (1, 2);

-- recipes and pictures
INSERT INTO pictures(id, url, public_id)
VALUES (2, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636201147/healthy-porridge-bowl-103f858_pwbecs.webp',
        'healthy-porridge-bowl-103f858_pwbecs');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (1, 5, '2011-11-05 13:09',
        'Start your day right with this filling bowl of oats, berries, banana and seeds. It''s healthy and packed with nutrients to fuel body and mind.',
        'EASY',
        2,
        '100g frozen raspberries
 1 orange , ½ sliced and ½ juiced
 150g porridge oats
 100ml milk
 ½ banana , sliced
 2 tbsp smooth almond butter
 1 tbsp goji berries
 1 tbsp chia seeds',
        'STEP 1
 Tip half the raspberries and all of the orange juice in a pan. Simmer until the raspberries soften, about 5 mins.

 STEP 2
 Meanwhile stir the oats, milk and 450ml water in a pan over a low heat until creamy. Top with the raspberry compote, remaining raspberries, orange slices, banana, almond butter, goji berries and chia seeds.',
        'Healthy porridge bowl',
        10, 1);
INSERT INTO meals(id, category, recipe_id)
VALUES (1, 'BREAKFAST', 1);


INSERT INTO pictures(id, url, public_id)
VALUES (3, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636207680/breakfast-burrito-b086548_noszay.webp',
        'breakfast-burrito-b086548_noszay');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (2, 10, '2021-11-06 16:10', 'Make a nutritious cocoon for breakfast ingredients with a wholemeal wrap. We’ve included protein-rich eggs and avocado to add good fats to this burrito.',
        'EASY', 3, '1 tsp chipotle paste
1 egg
1 tsp rapeseed oil
50g kale
7 cherry tomatoes, halved
½ small avocado, sliced
1 wholemeal tortilla wrap, warmed',
        'STEP 1
Whisk the chipotle paste with the egg and some seasoning in a jug. Heat the oil in a large frying pan, add the kale and tomatoes.

STEP 2
Cook until the kale is wilted and the tomatoes have softened, then push everything to the side of the pan. Pour the beaten egg into the cleared half of the pan and scramble. Layer everything into the centre of your wrap, topping with the avocado, then wrap up and eat immediately.',
        'Breakfast burrito', 5, 1);
INSERT INTO meals(id, category, recipe_id)
VALUES (2, 'BREAKFAST', 2);

INSERT INTO pictures(id, url, public_id)
VALUES (4, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636209163/eggrocketpizzas_5729-ebc7e0d_rnpesr.webp',
        'eggrocketpizzas_5729-ebc7e0d_rnpesr');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (3, 6, '2021-11-06 16:34',
        'The steak in this light salad is cooked on the barbecue, giving it a true taste of summer. It works brilliantly with our Asian-inspired dressing.',
        'EASY', 4, '2 x 250g sirloin sirloin steaks , fat trimmed
thumb-sized piece ginger , finely grated
1 garlic clove , finely grated
2 limes , juiced
2 tbsp sesame oil
1 tbsp low-salt soy sauce
3 red bird’s eye chillies , 2 finely chopped, 1 finely sliced
4 Little Gem lettuces
12 radishes , thinly sliced
3 carrots , peeled and finely sliced
½ cucumber , cut into ribbons using a peeler
3 spring onions , finely sliced
1 large ripe avocado , sliced
½ tbsp mixed sesame seeds',
        'STEP 1
Remove the steak from the fridge 1 hr before you’re ready to cook to bring it up to room temperature. Just before cooking, make the salad dressing by whisking together the ginger, garlic, lime juice, oil, soy and chopped chillies in a bowl.

STEP 2
Put the steaks on the barbecue and cook on one side for 3 mins, then turn and cook for 3 mins on the other side for medium rare. Alternatively, fry your steaks in a frying pan or griddle pan for 3 mins each side. After cooking, cover and rest the meat for 5 mins.

STEP 3
Arrange the lettuce leaves, radishes, carrot, cucumber, spring onion and avocado on a sharing plate. Slice the steak against the grain into thin slices and lay on top of the salad. Drizzle over any resting juices and the dressing. Garnish with the sesame seeds and the sliced red chilli.',
        'BBQ rainbow beef salad', 20, 1);
INSERT INTO meals(id, category, recipe_id)
VALUES (3, 'LUNCH', 3);


INSERT INTO pictures(id, url, public_id)
VALUES (5,
        'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636209985/recipe-image-legacy-id-1309544_3-94d5e02_wtqljr.webp',
        'recipe-image-legacy-id-1309544_3-94d5e02_wtqljr');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (4, 30, '2021-11-06 16:47',
        'A vibrant salad packed with yellow and red peppers, colourful onion, beef cooked to your liking and healthy grains.',
        'EASY', 5, '85g pearl barley , rinsed
1 red pepper , deseeded and cut into strips
1 yellow pepper , deseeded and cut into strips
1 red onion , cut into 8 wedges, leaving root intact
1 tbsp olive oil , plus a little extra
1 large lean steak , around 300g, trimmed of any excess fat
½ x 100g bag watercress , roughly chopped
juice ½ lemon , plus wedges to serve (optional)',
        'STEP 1
Put the pearl barley in a large pan of water. Bring to the boil and cook vigorously for 25-30 mins or until tender. Drain thoroughly and transfer to a bowl.

STEP 2
Meanwhile, heat oven to 200C/ 180C fan/gas 6. Put the peppers on a baking tray with the onion wedges, toss in 1 tbsp olive oil and roast for about 20 mins until tender.

STEP 3
While the peppers are roasting, rub the steak with a little bit of oil and season. Cook in a non-stick frying pan for 3-4 mins each side, or to your liking. Set aside to rest for a few mins. Mix the cooked peppers and onions into the barley. Stir though the watercress, lemon juice and some seasoning. Thinly slice the steaks, place on top of the salad and serve with lemon wedges, if you like.',
        'Steak, roasted pepper & pearl barley salad', 10, 1);
INSERT INTO meals(id, category, recipe_id)
VALUES (4, 'LUNCH', 4);


INSERT INTO pictures(id, url, public_id)
VALUES (6, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636210351/roasted-aloo-gobi-b125826_etzuzi.webp',
        'roasted-aloo-gobi-b125826_etzuzi');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (5, 50, '2021-11-06 16:53',
        'This extra special vegan curry uses roasted cauliflower and potatoes to bring out their flavour. You can also serve as a side to meat curries.',
        'EASY', 6,
        '400g floury potatoes (such as Maris Piper or King Edward), cut into medium-sized chunks
1 large cauliflower, cut into florets
1 tbsp cumin seeds
2 tsp coriander seeds
2 tsp nigella seeds
1 tsp ground cinnamon
1 tsp turmeric
1 tsp chilli powder
4 tbsp vegetable oil or sunflower oil or rapeseed oil
8 curry leaves
4 garlic cloves, crushed
2 x 400g cans chopped tomatoes
2 small green chillies, pierced a few times
1 tsp golden caster sugar
1 lime, juiced
small pack coriander, chopped
basmati rice, naan and natural yogurt, to serve',
        'STEP 1
Heat oven to 180C/160C fan/gas 4. Tip the potatoes into a large pan, fill with cold water and bring to the boil. Simmer for 5-6 mins until starting to soften but still holding their shape. Drain well.

STEP 2
On a large baking tray, toss the potatoes and cauliflower with the spices and 2 tbsp oil. Season well and roast for 45 mins, stirring halfway through cooking, until the veg is soft and starting to brown.

STEP 3
Meanwhile, heat the remaining oil in a large pan. Fry the curry leaves and garlic for 1 min, making sure the garlic doesn’t brown. Add the tomatoes, chillies, sugar, lime juice and some seasoning. Cover with a lid and simmer for 15 mins until the tomatoes have broken down.

STEP 4
Add the roasted veg to the tomatoes. Simmer for 5 mins, adding a splash of water if the curry gets too thick. Stir through the coriander and serve with rice, warm naan and yogurt.',
        'Roasted aloo gobi', 15, 1);
INSERT INTO meals(id, category, recipe_id)
VALUES (5, 'DINNER', 5);


INSERT INTO pictures(id, url, public_id)
VALUES (7, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636210588/chicken-sweetcorn-tacos-f01abbf_zykeoa.webp',
        'chicken-sweetcorn-tacos-f01abbf_zykeoa');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (6, 30, '2021-11-06 16:57',
        'Serve up these healthy chicken and sweetcorn tacos for an easy midweek meal that''s full of flavour. You''ll also have leftovers to make chicken and sweetcorn wraps for lunch tomorrow.',
        'MEDIUM', 7,
        '250g plain flour , plus extra for dusting
2 tbsp rapeseed oil
2 tbsp taco or fajita seasoning (see tip, below)
5-6 skinless chicken breasts, sliced
¼ red cabbage , finely shredded
3 limes , 1 juiced, 2 cut into wedges
small bunch of coriander , chopped
4 sweetcorn cob , kernels sliced off, or 400g frozen sweetcorn
400g can black beans , drained and rinsed
2 garlic cloves , crushed
4 tbsp fat-free yogurt , to serve
chilli sauce , to serve',
        'STEP 1
Combine the flour with half the oil and a small pinch of salt in a bowl. Pour over 125-150ml warm water, then bring together into a soft dough with your hands. Cut into six equal pieces, then cut four of the pieces in half again, so you have eight small pieces and two large. Roll all the pieces out on a floured work surface until they’re as thin as you can get them.

STEP 2
Heat a dry frying pan over a medium-high heat and cook the small and large tortillas for 2-3 mins on each side until golden and toasted (do this one at a time). Leave the large tortillas to cool, then cover and reserve for use in the lunchboxes (see tip below). Keep the small tortillas warm in foil.

STEP 3
Sprinkle the taco seasoning over the chicken in a bowl, and toss to combine. Toss the cabbage with the lime juice, half the coriander and some seasoning in another bowl, then leave to pickle.

STEP 4
Meanwhile, heat two frying pans over a high heat. Divide the remaining oil between the pans and fry the sweetcorn and a pinch of salt until sizzling and turning golden, stirring occasionally – you want the sweetcorn to char slightly, as this adds flavour, so you may need to leave it to cook undisturbed for a bit. While the sweetcorn cooks and chars, fry the chicken in the larger pan until cooked through and golden (you may need to do this in batches).

STEP 5
Tip the black beans and garlic into the sweetcorn and stir to warm through. Squeeze over two of the lime wedges.

STEP 6
Reserve two spoonfuls each of the chicken (about 1 chicken breast) and sweetcorn mix for use in the lunchboxes (see tip, below), then serve the rest in bowls alongside the cabbage, yogurt, lime wedges, remaining coriander, chilli sauce and tortillas for everyone to dig into.',
        'Chicken & sweetcorn tacos', 30, 1);
INSERT INTO meals(id, category, recipe_id)
VALUES (6, 'DINNER', 6);


INSERT INTO pictures(id, url, public_id)
VALUES (8, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636222932/Strawberry-Water-012_ocp8ma.webp',
        'Strawberry-Water-012_ocp8ma');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (7, 0, '2021-11-06 20:26',
        'Strawberry water is a deliciously hydrating drink with the essence of the red berry! Make a big pitcher and wow everyone.',
        'EASY', 8,
        '1 pound fresh strawberries
1 tablespoon lime juice
1 to 2 tablespoons sugar, to taste (or honey, maple syrup or agave nectar)
7 cups (64 ounces) cold water, divided
Sparkling water, to serve (optional)',
        'Clean the berries. Place the strawberries in a blender with strawberries with 1 cup water, the lime juice and sugar and blend on high until fully combined.
Add the remaining 6 cups cold water. Taste and adjust sugar or lime juice as needed (the flavor mellows over time). Serve immediately, or refrigerate for up to 3 days. If desired, serve topped off with sparkling water.',
        'Strawberry Water',
        5, 1);
INSERT INTO drinks(id, recipe_id)
VALUES (1, 7);


INSERT INTO pictures(id, url, public_id)
VALUES (9, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636223833/Strawberry-Smoothie-015_ggoeyj.jpg',
        'Strawberry-Smoothie-015_ggoeyj');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (8, 0, '2021-11-06 20:38',
        'Here’s the most perfect strawberry smoothie you’ll find! It’s easy to blend up and has the best creamy texture and fruity flavor.',
        'EASY', 9,
        '2 cups frozen strawberries
1 banana (room temperature)
¼ cup Greek yogurt*
1 cup milk (or almond milk or oat milk)
1 ½ tablespoons maple syrup, honey, or agave syrup
½ cup ice
Optional add-ins: 1 tablespoon almond butter, ¼ teaspoon vanilla, fresh mint leaves or basil leaves',
        '1. Place all ingredients in a blender, breaking the banana into pieces. Blend until creamy and frothy, stopping and scraping down the sides as necessary. If desired, garnish with a frozen strawberry and mint sprig. Serve immediately or store in a covered jar in the refrigerator for 2 days.',
        'Perfect Strawberry Smoothie', 5, 1);
INSERT INTO drinks(id, recipe_id)
VALUES (2, 8);


INSERT INTO pictures(id, url, public_id)
VALUES (10, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636225152/recipe-image-legacy-id-1029452_10-563fda8_bqwort.webp',
        'recipe-image-legacy-id-1029452_10-563fda8_bqwort');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (9, 0, '2021-11-06 20:53',
        'Three ingredients and two minutes is all you need to whip up this low-fat, low-calorie yogurt, which is ideal for eating after exercise.',
        'EASY', 10,
        '250g frozen mixed berry
250g 0%-fat Greek yogurt
1 tbsp honey or agave syrup',
        'STEP 1
Blend berries, yogurt and honey or agave syrup in a food processor for 20 seconds, until it comes together to a smooth ice-cream texture. Scoop into bowls and serve.',
        'Instant frozen berry yogurt', 2, 1);
INSERT INTO desserts(id, recipe_id)
VALUES (1, 9);

INSERT INTO pictures(id, url, public_id)
VALUES (11, 'https://res.cloudinary.com/di4ztc4mp/image/upload/v1636719256/Peanut-Butter-Oatmeal-Bars-013_shyx5j.jpg',
        'Peanut-Butter-Oatmeal-Bars-013_shyx5j');
INSERT INTO recipes(id, cook_time, created, description, difficulty, picture_id, ingredients, instructions, title,
                    prep_time, author_id)
VALUES (10, 0, '2021-11-06 21:07',
        'These peanut butter oatmeal bars are the best trick! They’re no bake, easy and healthy; and the flavor is out of this world.',
        'EASY', 11,
        '1 cup creamy peanut butter (no sugar added, or sunflower butter for nut free)
½ cup honey (or ½ cup agave syrup* for vegan)
4 cups Old Fashioned rolled oats
½ teaspoon kosher salt
½ teaspoon cinnamon
1 to 2 ounces dark chocolate (1/8 to 1/4 cup chocolate chips)',
        'Mix together the peanut butter, honey, oats, salt and cinnamon in a bowl. (If the mixture seems dry and not sticky, add a bit more peanut butter and/ or honey; different peanut butter brands perform differently.)
Add a sheet of parchment paper to a 9 x 9 pan. Place the ingredients in the pan and press it into an even layer. Use a small glass to roll over the top to get it smooth.
Freeze the bars for 10 minutes. Remove the pan from the freezer and use the parchment to lift it out of the pan. Cut into 20 rectangles (4 x 5 rows) or 40 small rectangles.
In the microwave with short intervals or over a double boiler, melt the chocolate, stirring until it comes together into a glossy chocolate. If using the microwave, use short bursts of 10 seconds or so and stir after each: be careful not to overheat the chocolate or get any water in the chocolate, which will cause it to seize up. When melted, drizzle over the bars and allow to cool.  You can eat right away, or refrigerate for about 1 hour for a more solid texture. Store refrigerated for up to 2 weeks (or frozen for several months, placing wax paper between the layers).',
        'Peanut Butter Oatmeal Bars', 15, 1);
INSERT INTO desserts(id, recipe_id)
VALUES (2, 10);