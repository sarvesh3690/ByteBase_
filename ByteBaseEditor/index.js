import express from "express";
import cors from "cors";
import session from "express-session";
import nodemailer from "nodemailer";
import fs from "fs";
//import { connectDB, getConnection } from "./DButils/dbutil.js";
import { exec } from "child_process";

const app = express();
app.use(express.json());
app.use(cors({
    origin: "*",
    methods: ['POST', 'PUT', 'GET', 'OPTIONS', 'HEAD'],
    credentials: true
}));
app.use(session({
    secret: "prithviraj",
    resave: false,
    saveUninitialized: true,
    cookie: {
        secure: false,  // Set to true for HTTPS
    },
}));

// const transport = nodemailer.createTransport({
//     host: "smtp.gmail.com",
//     port: 465,
//     secure: true,
//     auth: {
//         user: "temporary9665@gmail.com",
//         pass: "fkuw zykc jmop oejz"
//     }
// })




app.use(express.text());

app.post("/editor", (req, res) => {
    const data = req.body;


    try {
        fs.writeFileSync("Practice.java", data, "utf8");
    } catch (err) {
        console.error("File write error:", err);
        return res.status(500).send({ msg: "Error writing file" });
    }


    exec("javac Practice.java", (err, stdout, stderr) => {
        if (err || stderr) {
            console.error("Compilation error:", err || stderr);
            return res.status(203).send({ msg: stderr });
        }


        exec("java Practice", (err, stdout, stderr) => {

            try {
                fs.unlinkSync("Practice.java");
                fs.unlinkSync("Practice.class");
            } catch (cleanupErr) {
                console.error("Cleanup error:", cleanupErr);
            }

            if (err || stderr) {
                console.error("Execution error:", err || stderr);
                return res.status(203).send({ msg: stderr });
            }


            res.status(200).send({ msg: stdout });
        });
    });
});


app.listen(9800, () => {
    console.log("Server is running on port 9800...");
   
});
