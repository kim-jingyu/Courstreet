import styled from "styled-components";

export const SignupWrapper = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
`

export const SignupContainer = styled.div`
    width: 100%;
    max-width: 400px;
    padding: 30px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    text-align: center;
    position: relative;
` 

export const SubmitForm = styled.form`
    width: 100%;
    display: flex;
    flex-direction: column;
`

export const Title = styled.h1`
    font-size: 24px;
    margin-bottom: 40px;
`

export const Label = styled.label`
    text-align: left;
    margin-bottom: 10px;
`

export const TextForm = styled.span`
    display: inline-block;
    vertical-align: middle;
    line-height: 1;
`

export const InputForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 10px;
`

export const InputField = styled.input`
    margin-bottom: 10px;
    margin-top: 5px;
    width: 80%;
    /* margin-left: 10px; */
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;

    ::placeholder {
        color: #C4C4C4;
        font-size: 10px;
    }
`

export const GenderLabelEx = styled.label`
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    text-align: left;
    width: 100%;
`

export const GenderContainer = styled.div`
    display: flex;
    flex-grow: 1;
    margin-left: 10px;
`

export const GenderLabelIn = styled.label`
    display: flex;
    flex-direction: row;
    align-items: center;
`

export const GenderRadio = styled.input`
  margin-right: 5px;
`

export const AgeLabel = styled.label`
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    text-align: left;
    width: 100%;
`

export const Select = styled.select`
    width: 20%;
    padding: 8px;
    margin-top: 5px;
    margin-left: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    height: 32px; /* 원하는 높이로 조정 */
    // vertical-align: middle; /* 텍스트를 수직으로 정렬 */
`

export const Option = styled.option``

export const SubmitButton = styled.button`
    background-color: #000;
    color: #fff;
    border: none;
    padding: 10px;
    border-radius: 4px;
    cursor: pointer;
    margin-top: 20px;

    &:hover {
        background-color: #444;
    }
`