<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oath Certificate</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Great+Vibes&family=Open+Sans:wght@400;700&display=swap');
        body {
            font-family: 'Open Sans', sans-serif;
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
            text-align: center;
            position: relative; /* Add this line */
        }
        h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
            color: #333;
            display: none; /* Hides the title */
        }
        .certificate {
            border: 10px solid #d4af37;
            padding: 20px;
            border-radius: 12px;
            margin-top: 20px;
            text-align: center;
            background: #f7f7f7;
            background-image: url('https://www.toptal.com/designers/subtlepatterns/patterns/diagonal_striped_brick.png');
            background-size: cover;
            position: relative;
            overflow: hidden;
        }
        .certificate h2 {
            font-family: 'Great Vibes', cursive;
            font-size: 3em;
            margin-top: 0;
            color: #d4af37;
        }
        .certificate p {
            margin: 10px 0;
            font-size: 1.1em;
        }
        .certificate .name {
            font-size: 1.5em;
            font-weight: bold;
            color: #d4af37;
        }
        .certificate .email {
            font-size: 1.2em;
            font-style: italic;
            color: #333;
        }
        .certificate .date {
            position: absolute;
            top: 20px;
            right: 20px;
            font-family: 'Open Sans', sans-serif;
            font-size: 1.2em;
            color: #333;
        }
        .certificate .static-number {
            position: absolute;
            top: 20px;
            left: 20px;
            font-family: 'Open Sans', sans-serif;
            font-size: 1.2em;
            color: #333;
        }
        .certificate .signature {
            position: absolute;
            bottom: 20px;
            right: 20px;
            font-family: 'Great Vibes', cursive;
            font-size: 1.5em;
            color: #d4af37;
        }
        .certificate .seal {
            position: absolute;
            bottom: 20px;
            right: 20px;
            width: 100px;
            height: 100px;
            background: url('https://upload.wikimedia.org/wikipedia/commons/e/eb/Gold_Seal_Vector.svg') no-repeat center center;
            background-size: contain;
            opacity: 0.8;
        }
        .print-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #28a745;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s, box-shadow 0.3s;
            margin-top: 10px;
        }
        .print-button:hover {
            background-color: #218838;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="certificate" id="certificate">
            <div class="static-number">00<%= request.getAttribute("id") %></div>
            <div class="date" id="currentDate"></div>
            <h2>Certificate of Oath</h2>
            <p>This is to certify that</p>
            <p class="name"><%= request.getAttribute("name") %></p>
            <p>with the email address</p>
            <p class="email"><%= request.getAttribute("email") %></p>
            <p>has taken the following oath:</p>
            <p>India is my country; all Indians are my brothers and sisters.<br>
            I love my country, and I am proud of its rich and varied heritage. I shall always strive to be worthy of it.<br>
            I shall respect my parents, teachers and all elders and treat everyone with courtesy.<br>
            To my country and my people, I pledge my devotion.<br>
            In their well being and prosperity lies my happiness.</p>
            <p>भारत मेरा देश है; सभी भारतीय मेरे भाई और बहन हैं।<br>
            मुझे अपने देश से प्यार है, और मैं इसके समृद्ध और विविध विरासत पर गर्व करता हूँ। मैं हमेशा इसके योग्य बनने की कोशिश करूंगा।<br>
            मैं अपने माता-पिता, शिक्षकों और सभी बुजुर्गों का सम्मान करूंगा और सभी के साथ शिष्टता से पेश आऊँगा।<br>
            अपने देश और अपनी जनता के प्रति, मैं अपनी भक्ति की प्रतिज्ञा करता हूँ।<br>
            उनकी भलाई और समृद्धि में ही मेरी खुशी है।</p>
            <div class="signature">Kapil Agrawal</div>
            <div class="seal"></div>
        </div>
        <button class="print-button" onclick="printCertificate()">Print Certificate</button>
    </div>

    <script>
        function printCertificate() {
            const printContents = document.getElementById('certificate').outerHTML;
            const originalContents = document.body.innerHTML;

            document.body.innerHTML = printContents;
            window.print();
            document.body.innerHTML = originalContents;
            window.location.reload(); // Reload the page to restore original content
        }

        function updateDate() {
            const currentDate = new Date();
            const formattedDate = currentDate.toLocaleDateString('en-GB', {
                day: 'numeric',
                month: 'long',
                year: 'numeric'
            });
            document.getElementById('currentDate').innerText = `Date: ${formattedDate}`;
        }

        window.onload = updateDate;
    </script>
</body>
</html>

