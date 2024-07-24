<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form with Oath</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
            text-align: center;
        }
        .container h1 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #333;
        }
        .container p {
            font-size: 16px;
            color: #555;
            text-align: left;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .form-group label {
            display: block;
            font-size: 16px;
            margin-bottom: 5px;
            color: #555;
        }
        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="tel"] {
            width: calc(100% - 20px);
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-sizing: border-box;
        }
        .checkbox-group {
            margin-bottom: 20px;
            text-align: left;
        }
        .checkbox-group input[type="checkbox"] {
            margin-right: 10px;
        }
        .button,
        .submit-button {
            display: inline-block;
            padding: 15px 30px;
            font-size: 18px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s, box-shadow 0.3s;
            margin: 5px;
        }
        .button:hover,
        .submit-button:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }
        .submit-button {
            display: none; /* Hidden by default */
        }
        .checkbox-group input[type="checkbox"]:checked ~ .submit-button {
            display: inline-block; /* Show submit button when checkbox is checked */
        }
        .form-group input:invalid {
            border-color: red;
        }
        .form-group input:valid {
            border-color: green;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>OATH // शपथ</h1>
        <p id="oath-text-en">
            India is my country; all Indians are my brothers and sisters.<br>
            I love my country, and I am proud of its rich and varied heritage. I shall always strive to be worthy of it.<br>
            I shall respect my parents, teachers and all elders and treat everyone with courtesy.<br>
            To my country and my people, I pledge my devotion.<br>
            In their well being and prosperity lies my happiness.
        </p>
        <p id="oath-text-hi">
            भारत मेरा देश है; सभी भारतीय मेरे भाई और बहन हैं।<br>
            मुझे अपने देश से प्यार है, और मैं इसके समृद्ध और विविध विरासत पर गर्व करता हूँ। मैं हमेशा इसके योग्य बनने की कोशिश करूंगा।<br>
            मैं अपने माता-पिता, शिक्षकों और सभी बुजुर्गों का सम्मान करूंगा और सभी के साथ शिष्टता से पेश आऊँगा।<br>
            अपने देश और अपनी जनता के प्रति, मैं अपनी भक्ति की प्रतिज्ञा करता हूँ।<br>
            उनकी भलाई और समृद्धि में ही मेरी खुशी है।
        </p>
        <form id="registration-form" action="cert" method="post">
            <div class="form-group">
                <label for="mobile">Mobile Number</label>
                <input type="tel" id="mobile" name="mobile" pattern="\d{10}" required placeholder="Enter 10 digit mobile number">
            </div>
            <div class="checkbox-group">
                <input type="checkbox" id="agree" name="agree" required>
                <label for="agree">I agree to the oath</label>
            </div>
            <button type="button" class="button" onclick="readOath()">Read Aloud</button>
            <button type="button" class="button" onclick="stopOath()">Stop</button>
            <button type="submit" class="submit-button">Submit</button>
        </form>
    </div>

    <script>
        let utterance;

        function readOath() {
            const textEn = document.getElementById('oath-text-en').innerText;
            const textHi = document.getElementById('oath-text-hi').innerText;

            if (utterance) {
                speechSynthesis.cancel(); // Stop any current speech
            }

            utterance = new SpeechSynthesisUtterance(textEn);
            utterance.lang = 'en-US'; // Set language for English

            utterance.onend = () => {
                const hindiUtterances = textHi.split('।').filter(sentence => sentence.trim().length > 0).map(sentence => {
                    const u = new SpeechSynthesisUtterance(sentence.trim());
                    u.lang = 'hi-IN'; // Set language for Hindi
                    return u;
                });

                hindiUtterances.forEach((utt, idx) => {
                    utt.onend = () => {
                        if (idx < hindiUtterances.length - 1) {
                            speechSynthesis.speak(hindiUtterances[idx + 1]);
                        }
                    };
                });

                if (hindiUtterances.length > 0) {
                    speechSynthesis.speak(hindiUtterances[0]);
                }
            };

            speechSynthesis.speak(utterance);
        }

        function stopOath() {
            speechSynthesis.cancel(); // Stop the speech
        }

        document.getElementById('agree').addEventListener('change', validateForm);
        document.getElementById('mobile').addEventListener('input', validateForm);

        function validateForm() {
            const submitButton = document.querySelector('.submit-button');
            const mobile = document.getElementById('mobile').value;
            const agree = document.getElementById('agree').checked;

            if (agree && mobile.length === 10) {
                submitButton.style.display = 'inline-block';
            } else {
                submitButton.style.display = 'none';
            }
        }
    </script>
</body>
</html>
